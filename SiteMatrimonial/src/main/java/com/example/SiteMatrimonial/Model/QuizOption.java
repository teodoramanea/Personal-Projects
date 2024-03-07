package com.example.SiteMatrimonial.Model;

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
public class QuizOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String answerOption;

    @ManyToOne
    private QuizQuestion quizQuestion;
}
