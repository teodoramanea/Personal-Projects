
package com.example.SiteMatrimonial.Repository;

import com.example.SiteMatrimonial.Model.Profile;

import com.example.SiteMatrimonial.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
    Profile findFirstById(Integer id);
    void deleteById(Integer id);
    void deleteByUserId(Integer id);

   // @Modifying
   // @Query("DELETE FROM Chat c WHERE c.user = :user")
    void deleteByUser(User user);
    Profile readById(Integer id);

}
