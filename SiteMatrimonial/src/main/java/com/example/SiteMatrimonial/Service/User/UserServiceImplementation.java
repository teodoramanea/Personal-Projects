
package com.example.SiteMatrimonial.Service.User;

import com.example.SiteMatrimonial.Model.Profile;
import com.example.SiteMatrimonial.Model.User;
import com.example.SiteMatrimonial.Repository.ChatRepo;
import com.example.SiteMatrimonial.Repository.ProfileRepo;
import com.example.SiteMatrimonial.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {


    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private ChatRepo chatRepo;

    public UserServiceImplementation() throws SQLException {
    }

    @Override
    public User findFirstById(Integer id) {
        return userRepository.findFirstById(id);
    }

    @Override
    public User insert(User user) {

        User newUser = userRepository.save(user);

        Profile profile = new Profile();
        profile.setAge(0);
        profile.setCity("");
        profile.setStatus("");
        profile.setName("");
        profile.setOccupation("");
        profile.setShortDescription("");
        profile.setReligion("");
        profile.setDivorced(false);
        profile.setFemale(false);
        profile.setMale(false);
        profile.setKids(false);

        profile.setUser(newUser);
        newUser.setProfile(profile);
        profileRepo.save(profile);


        System.out.println(newUser.getProfile().getId());
        return newUser;
    }

    @Override
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    @Override
    public User read(Integer id) {
        return userRepository.readById(id);
    }


    public User updateById(Integer userId, User user) {
        User user1 = userRepository.findFirstById(userId);
        user1.setAdmin(user.getAdmin());
        user1.setDisableQuiz(user.getDisableQuiz());
        user1.setName(user.getName());
        user1.setAddress(user.getAddress());
        user1.setPassword(user.getPassword());
        user1.setEmailAddress(user.getEmailAddress());
        user1.setTelNumber(user.getTelNumber());
        return userRepository.save(user1);
    }

    @Override
    public User findFirstByEmailAddress(String email) {
        return userRepository.findFirstByEmailAddress(email);
    }


    public List<User> ReadAllUsers() {
        List<User> allUsers = (List<User>) userRepository.findAll();
        List<User> modifiedUsers = new ArrayList<>();

        for (User user : allUsers) {
            User modifiedUser = new User();
            modifiedUser.setId(user.getId());
            modifiedUser.setAdmin(user.getAdmin());
            modifiedUser.setDisableQuiz(user.getDisableQuiz());
            modifiedUser.setName(user.getName());
            modifiedUser.setAddress(user.getAddress());
            modifiedUser.setTelNumber(user.getTelNumber());
            modifiedUser.setEmailAddress(user.getEmailAddress());
            modifiedUser.setPassword(user.getPassword());

            modifiedUsers.add(modifiedUser);
        }

        return modifiedUsers;
    }

    @Override
    public void disableQuiz(Integer id, Boolean disableQuiz) {
        User user = userRepository.findFirstById(id);
        user.setDisableQuiz(disableQuiz);
        userRepository.save(user);
    }

}

