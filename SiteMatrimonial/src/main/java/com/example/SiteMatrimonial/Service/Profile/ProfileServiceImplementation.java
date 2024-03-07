
package com.example.SiteMatrimonial.Service.Profile;

import com.example.SiteMatrimonial.Model.Profile;
import com.example.SiteMatrimonial.Model.User;
import com.example.SiteMatrimonial.Repository.ProfileRepo;
import com.example.SiteMatrimonial.Repository.UserRepo;
import com.example.SiteMatrimonial.Service.Profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImplementation implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Profile findFirstById(Integer id) {
        return null;
    }

    @Override
    public void insert(Profile profile) {
        profileRepo.save(profile);
    }

    @Override
    public void delete(Integer id) {
        profileRepo.deleteById(id);
    }

    @Override
    public Profile read(Integer id) {
        User user = userRepo.findFirstById(id);
        Integer profileId = user.getProfile().getId();

        return  profileRepo.readById(profileId);
    }

    @Override
    public List<String> readAllProfileNames(List<Integer> matchIds) {
        List<String> names = new ArrayList<>();
        System.out.println(matchIds);
        for(Integer id : matchIds)
        {
            User user = userRepo.findFirstById(id);
           // System.out.println(user.getProfile().getCity());
            names.add(user.getProfile().getName());

        }
        return names;
    }


    @Override
    public Profile updateById(Integer userId, Profile profile){

        User user = userRepo.findFirstById(userId);
        System.out.println(user.getId());
      //  System.out.println(profile.setUser(userRepo.findFirstById(userId)));
      // User user = userRepo.findFirstById(profile.getUser().getId());
      // System.out.println(user.getId());

       profile.setUser(user);

      return  profileRepo.save(profile);
    }

    @Override
    public List<Profile> ReadAllProfiles() {
        List<Profile> allProfiles = (List<Profile>) profileRepo.findAll();
        List<Profile> modifiedProfiles = new ArrayList<>();

        for (Profile profile : allProfiles) {
            Profile modifiedProfile = new Profile();
            modifiedProfile.setId(profile.getId());
            modifiedProfile.setName(profile.getName());
            modifiedProfile.setAge(profile.getAge());
            modifiedProfile.setCity(profile.getCity());
            modifiedProfile.setStatus(profile.getStatus());
            modifiedProfile.setOccupation(profile.getOccupation());
            modifiedProfile.setShortDescription(profile.getShortDescription());
            modifiedProfile.setReligion(profile.getReligion());
            modifiedProfile.setDivorced(profile.getDivorced());
            modifiedProfile.setKids(profile.getKids());
            modifiedProfile.setMale(profile.getMale());
            modifiedProfile.setFemale(profile.getFemale());

            modifiedProfiles.add(modifiedProfile);
        }

        return modifiedProfiles;
    }

    @Override
    public Integer findUserId(Integer profileId) {
        Profile profile = profileRepo.findFirstById(profileId);
        return profile.getUser().getId();
    }

}


