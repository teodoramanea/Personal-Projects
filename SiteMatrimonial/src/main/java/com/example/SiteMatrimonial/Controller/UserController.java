package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Model.QuizResponse;
import com.example.SiteMatrimonial.Model.User;
import com.example.SiteMatrimonial.Service.Profile.ProfileServiceImplementation;
import com.example.SiteMatrimonial.Service.User.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/User")
public class UserController {
    private final UserServiceImplementation userServiceImplementation;
    @GetMapping("/GetData")

    public String getMessage()
    {
        return "teo ce faci??";
    }

    @PostMapping("/Insert")
    public User insert(@RequestBody User user)
    {
        return userServiceImplementation.insert(user);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam("id") Integer id)
    {
        userServiceImplementation.delete(id);
    }

    @GetMapping("/Read")
    public void read(@RequestParam("id") Integer id)
    {
        userServiceImplementation.read(id);

    }
    @GetMapping("/ReadAll")
    public List<User> readAllUsers()
    {
        return userServiceImplementation.ReadAllUsers();
    }

    @PutMapping  ("/Update")
    public User update(@RequestParam("id") Integer userId, @RequestBody User user)
    {

        return userServiceImplementation.updateById(userId, user);

    }

    @PostMapping("/DisableQuiz")
    public void DisableQuiz(@RequestParam("id")Integer id, @RequestParam("disableQuiz") Boolean disableQuiz)
    {
        userServiceImplementation.disableQuiz(id, disableQuiz);
    }

    @GetMapping("/Login")
    public User Login(@RequestParam("emailAddress") String email, @RequestParam("password")  String password)
    {
        User user = userServiceImplementation.findFirstByEmailAddress(email);
        if(user == null)
            return new User();

        if(!user.getPassword().equals(password))
            return new User();

        if(user.getProfile() != null)
            user.getProfile().getUser().setProfile(null);
        if(user.getMatchList() != null)
        {
            for(Matchul match: user.getMatchList())
                match.setUser(null);
        }

        if(user.getQuizResponses() != null)
        {
            for(QuizResponse quizResponse : user.getQuizResponses())
            {
                quizResponse.setUser(null);
                quizResponse.setQuizQuestion(null);
            }
        }

        return user;
    }
}
