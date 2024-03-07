package com.example.SiteMatrimonial.Service.QuizResponse;

import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.example.SiteMatrimonial.Model.QuizResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuizResponseService {
    QuizResponse findFirstById(Integer id);
    void insert(Integer userId, List<Integer> quizQuestions,  List<QuizResponse> quizResponses);
    void delete(Integer id);
    QuizResponse read(Integer id);

   // void updateById(Integer id);
}
