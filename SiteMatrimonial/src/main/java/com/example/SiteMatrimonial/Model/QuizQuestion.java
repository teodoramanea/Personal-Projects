package com.example.SiteMatrimonial.Model;


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
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String question;


    @OneToMany(mappedBy = "quizQuestion", cascade = CascadeType.REMOVE)
    private List<QuizResponse> quizResponseList;

    @OneToMany (mappedBy = "quizQuestion", cascade = CascadeType.REMOVE)
    private List<QuizOption> answerOptionsList;


}
