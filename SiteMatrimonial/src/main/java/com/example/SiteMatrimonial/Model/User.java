
package com.example.SiteMatrimonial.Model;

import com.example.SiteMatrimonial.Model.Chat;
import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Boolean admin;
    private Boolean disableQuiz;
    private String name;
    private String address;
    private Integer telNumber;
    private String emailAddress;
    private String password;


    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)

    private List<Matchul> matchList;


    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<QuizResponse> quizResponses;


    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Chat> chatList;

   // @ManyToOne
    //private Chat chat;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;

  /*  public User(Integer id, String name, String address, Integer telNumber, String emailAddress, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telNumber = telNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }*/


}