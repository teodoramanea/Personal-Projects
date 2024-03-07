package com.example.SiteMatrimonial.Repository;

import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.example.SiteMatrimonial.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface QuizQuestionRepo  extends CrudRepository<QuizQuestion, Integer> {

    QuizQuestion findFirstById(Integer id);
    void deleteById(Integer id);
    QuizQuestion readById(Integer id);
}
