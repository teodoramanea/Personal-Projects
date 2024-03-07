
package com.example.SiteMatrimonial.Service.Match;

import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.example.SiteMatrimonial.Model.QuizResponse;
import com.example.SiteMatrimonial.Model.User;
import com.example.SiteMatrimonial.Repository.MatchRepo;
import com.example.SiteMatrimonial.Repository.PreferencesRepo;
import com.example.SiteMatrimonial.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MatchServiceImplementation implements MatchService{
    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Matchul findFirstById(Integer id) {
        return matchRepo.findFirstById(id);
    }

    public List<Integer> extractUserIdsExceptGiven(Integer userId, List<User> users) {
        return users.stream()
                .filter(user -> !user.getId().equals(userId))
                .map(User::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void insertMultipleMatches(Integer userId) {
        ///verifica daca nu exista deja in tabela match
        User userul = userRepo.findFirstById(userId);
        Matchul matchul = matchRepo.findFirstByUser(userul);
        if(matchul != null)
            return;
        ///aici instantiezi un match si ii setezi match_user_id ul cu ce trebuie
        User user = userRepo.findFirstById(userId);
       // System.out.println(user.getId());
        List<QuizResponse> user_responses = user.getQuizResponses();
        List<Matchul> matchList = new ArrayList<Matchul>();


      //  System.out.println("inainte");

        List<User> users = (List<User>) userRepo.findAll();
//        for(User user1 : users)
//            System.out.println(user1.getId());
        List<User> potential_match = new ArrayList<>();

        for(User user1 : users)
        {
            if(!Objects.equals(user1.getId(), userId))
            {
                potential_match.add(user1);
            }

        }
        for(User user1 : potential_match)
            System.out.println(user1.getId());

          for(User pot_match : potential_match)
        {
            int count = 0; //numara cate raspunsuri au in comun
            List<QuizResponse> match_responses = pot_match.getQuizResponses();
          //  System.out.println(match_responses);
         //   for(QuizResponse quizResponse : match_responses)
         //       System.out.println(quizResponse.getId_option());

           for(int i = 0; i < match_responses.size(); i++)
            {
               if(user_responses.get(i).getQuizQuestion() == match_responses.get(i).getQuizQuestion())
                {
                    if(Objects.equals(user_responses.get(i).getId_option(), match_responses.get(i).getId_option()))
                        count++;
                }
            }
            //System.out.println(count);
            if(count >= 3)
            {
                ///varianta simplificata
                Matchul match = new Matchul();
                match.setMatch_user_id(pot_match.getId());
             //   List<Matchul> matchulList1 = user.getMatchList();
              //  matchulList1.add(match);
              //  user.setMatchList(matchulList1);
                match.setUser(user);

                for(Matchul user1 : user.getMatchList())
                    System.out.println(user1.getMatch_user_id());

                Matchul match2 = new Matchul();
                match2.setMatch_user_id(userId);
            //    List<Matchul> matchulList2 = pot_match.getMatchList();
             //   matchulList2.add(match2);
              //  pot_match.setMatchList(matchulList2);
                match2.setUser(userRepo.findFirstById(pot_match.getId()));

                for(Matchul user1 : pot_match.getMatchList())
                    System.out.println(user1.getMatch_user_id());

                matchRepo.save(match);
                matchRepo.save(match2);


            }
        }
    }


    @Override
    public void delete(Integer userId, Integer match_user_id){

        User user = userRepo.findFirstById(userId);
        Matchul matchul = matchRepo.findFirstByUser(user);

        User user1 = userRepo.findFirstById(match_user_id);
        Matchul matchul1 = matchRepo.findFirstByUser(user1);

        matchRepo.deleteById(matchul.getId());
        matchRepo.deleteById(matchul1.getId());

    }

    @Override
    public Matchul read(Integer id) {
        return matchRepo.readById(id);
    }

    @Override
    public List<Integer> readMatchIds(Integer userId) {
        User user = userRepo.findFirstById(userId);
        List<Integer> matchIds = new ArrayList<>();
        for(Matchul matchul : user.getMatchList())
        {
            matchIds.add(matchul.getMatch_user_id());
        }
        return matchIds;
    }

}

