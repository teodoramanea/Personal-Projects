package com.example.SiteMatrimonial.Service.QuizOption;

import com.example.SiteMatrimonial.Model.QuizOption;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuizOptionService {
    QuizOption findFirstById(Integer id);
    void insert(Integer id, QuizOption quizOption);
    void delete(Integer id);
    QuizOption read(Integer id);

    public List<String> ReadAllOptions();
    public List<QuizOption> ReadAll();
    public QuizOption updateById(Integer id, QuizOption option);
}
