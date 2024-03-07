
package com.example.SiteMatrimonial.Service.Match;
import com.example.SiteMatrimonial.Model.Matchul;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MatchService {

    Matchul findFirstById(Integer id);
    void insertMultipleMatches(Integer userId);
    public void delete(Integer userId, Integer match_user_id);
    Matchul read(Integer id);

    List<Integer> readMatchIds(Integer userId);

   // void updateById(Integer id, Integer  profileId);

}

