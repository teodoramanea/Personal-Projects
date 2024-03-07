
package com.example.SiteMatrimonial.Service.User;

import com.example.SiteMatrimonial.Model.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserService {
     User findFirstById(Integer id);
     User insert(User user);
     void delete(Integer id);
     User read(Integer id);

     public User updateById(Integer userId, User user);
    // User readByEmail(String email);
     User findFirstByEmailAddress(String email);

    public List<User> ReadAllUsers();

    public void disableQuiz(Integer id, Boolean disableQuiz);


}

