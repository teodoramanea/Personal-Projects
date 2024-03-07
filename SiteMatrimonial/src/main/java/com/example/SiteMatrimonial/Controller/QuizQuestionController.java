package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.example.SiteMatrimonial.Service.QuizQuestion.QuizQuestionServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/QuizQuestion")
public class QuizQuestionController {
    private final QuizQuestionServiceImplementation quizQuestionServiceImplementation;
    @PostMapping("/Insert")
    public void insert(@RequestBody QuizQuestion quizQuestion)
    {
        quizQuestionServiceImplementation.insert(quizQuestion);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam("id")  Integer id)
    {
        quizQuestionServiceImplementation.delete(id);
    }

    @GetMapping("/Read")
    public void read(@RequestParam("id") Integer id)
    {
        quizQuestionServiceImplementation.read(id);
    }

    @GetMapping("/ReadAll")
    public List<String>  ReadAll(){
        return  quizQuestionServiceImplementation.ReadAllQuestions();
    }

    @GetMapping("/ReadAlll")
    public List<QuizQuestion> ReadAlll() {
        return quizQuestionServiceImplementation.ReadAll();

    }

    @PutMapping  ("/Update")
    public void update(@RequestParam("id") Integer questionId, @RequestBody  QuizQuestion quizQuestion)
    {

        quizQuestionServiceImplementation.updateById(questionId, quizQuestion);

    }

}
