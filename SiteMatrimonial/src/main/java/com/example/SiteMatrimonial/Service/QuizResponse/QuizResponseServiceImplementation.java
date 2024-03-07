package com.example.SiteMatrimonial.Service.QuizResponse;

import com.example.SiteMatrimonial.Model.*;
import com.example.SiteMatrimonial.Repository.QuizQuestionRepo;
import com.example.SiteMatrimonial.Repository.QuizResponseRepo;
import com.example.SiteMatrimonial.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
public class QuizResponseServiceImplementation implements QuizResponseService{

    @Autowired
    private QuizResponseRepo quizResponseRepo;

    @Autowired
    private QuizQuestionRepo quizQuestionRepo;
    @Autowired
    private UserRepo userRepo;

    public QuizResponseServiceImplementation() throws SQLException {
    }

    @Override
    public QuizResponse findFirstById(Integer id) {
        return quizResponseRepo.findFirstById(id);
    }

    @Override
    public void insert(Integer userId, List<Integer> quizQuestions, List<QuizResponse> quizResponses) {
        for(QuizResponse quizResponse : quizResponses)
        {
            System.out.println(quizResponse.getId_option());
        }
        User user = userRepo.findFirstById(userId);
        int index = 0;
       for(Integer id : quizQuestions)
        {
            QuizQuestion quizQuestion = quizQuestionRepo.findFirstById(id);
            List<QuizResponse> quizResponseList = quizQuestion.getQuizResponseList();
            for (QuizResponse response : quizQuestion.getQuizResponseList()) {
                System.out.print(response.getId_option());
            }
            quizResponseList.add(quizResponses.get(index));
            quizQuestion.setQuizResponseList(quizResponseList);


            quizResponses.get(index).setQuizQuestion(quizQuestion);
            index++;
        }


        user.setQuizResponses(quizResponses);

        for (QuizResponse quizResponse : quizResponses) {
             quizResponse.setUser(user);
        }

        quizResponseRepo.saveAll(quizResponses);
        for(QuizResponse quizResponse : user.getQuizResponses())
        {
            System.out.println(quizResponse.getId_option());
        }
    }

   /* @Override
    public void insert(QuizResponse quizResponse) {
        quizResponseRepo.save(quizResponse);
    }*/

    @Override
    public void delete(Integer id) {
        quizResponseRepo.deleteById(id);
    }

    @Override
    public QuizResponse read(Integer id) {
        return quizResponseRepo.readById(id);
    }

    final String DB_URL = "jdbc:mysql://localhost:3306/sitematrimonial";
    final String USERNAME = "root";
    final String PASSWORD = "teo2002";
    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
   // @Override
  /*  public void updateById(Integer id) {

        String sql = "UPDATE quizresponse SET response = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, response);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
