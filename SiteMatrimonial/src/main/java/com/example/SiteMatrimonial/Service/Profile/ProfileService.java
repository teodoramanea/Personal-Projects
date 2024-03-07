
package com.example.SiteMatrimonial.Service.Profile;

import com.example.SiteMatrimonial.Model.Profile;

import com.example.SiteMatrimonial.Model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProfileService {
    Profile findFirstById(Integer id);
    void insert(Profile profile);
    void delete(Integer id);
    Profile read(Integer id);

    List<String> readAllProfileNames(List<Integer> match_ids);

      // Profile updateById (Integer id, String name, Integer age, String city, String status, String occupation, String shortDescription,
        //          String religion, Boolean divorced, Boolean kids, Boolean male, Boolean female);
    Profile updateById(Integer userId, Profile profile);
    public List<Profile> ReadAllProfiles();
    public Integer findUserId(Integer profileId);
}

