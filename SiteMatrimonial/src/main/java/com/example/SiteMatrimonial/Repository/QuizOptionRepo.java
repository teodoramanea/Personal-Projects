package com.example.SiteMatrimonial.Repository;

import com.example.SiteMatrimonial.Model.QuizOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface QuizOptionRepo extends CrudRepository<QuizOption, Integer> {
    QuizOption findFirstById(Integer id);
    void deleteById(Integer id);
    QuizOption readById(Integer id);
}
