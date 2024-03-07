package com.example.SiteMatrimonial.Repository;
import com.example.SiteMatrimonial.Model.QuizResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface QuizResponseRepo extends CrudRepository<QuizResponse, Integer> {

    QuizResponse findFirstById(Integer id);
    void deleteById(Integer id);
    QuizResponse readById(Integer id);
}
