
package com.example.SiteMatrimonial.Repository;

import com.example.SiteMatrimonial.Model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstById(Integer id);
    void deleteById(Integer id);

    User findFirstByEmailAddress(String email);

    //List<User> findAllByTelNumber(Integer number);
    User readById(Integer id);


}
