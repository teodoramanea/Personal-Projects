
package com.example.SiteMatrimonial.Repository;


import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface MatchRepo extends CrudRepository<Matchul, Integer> {
    Matchul findFirstById(Integer id);

    //Matchul findFirstByMatch_user_id(Integer match_user_id);
    Matchul findFirstByUser(User user);
    void deleteById(Integer id);

    Matchul readById(Integer id);


}
