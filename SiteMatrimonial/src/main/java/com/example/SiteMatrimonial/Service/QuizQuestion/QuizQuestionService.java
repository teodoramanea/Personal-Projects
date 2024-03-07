package com.example.SiteMatrimonial.Service.QuizQuestion;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuizQuestionService {
    QuizQuestion findFirstById(Integer id);
    void insert(QuizQuestion quizQuestion);
    void delete(Integer id);
    QuizQuestion read(Integer id);

    public List<String> ReadAllQuestions();
    public  List<QuizQuestion> ReadAll();

    public QuizQuestion updateById(Integer questionId, QuizQuestion question);

    //public  QuizQuestion readAllIdsQuestions();

}
