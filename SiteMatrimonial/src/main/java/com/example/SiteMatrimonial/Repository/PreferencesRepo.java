
package com.example.SiteMatrimonial.Repository;

import com.example.SiteMatrimonial.Model.Preferences;
import com.example.SiteMatrimonial.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PreferencesRepo extends CrudRepository<Preferences, Integer> {
    Preferences findFirstById(Integer id);
    void deleteById(Integer id);
    Preferences readById(Integer id);


}

