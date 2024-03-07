package com.example.SiteMatrimonial.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Repository;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository

public class QuizResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer id_option;


    @ManyToOne
    private User user;


    @ManyToOne
    private QuizQuestion quizQuestion;

}
