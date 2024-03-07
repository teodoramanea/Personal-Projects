package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.QuizResponse;

import com.example.SiteMatrimonial.Service.QuizResponse.QuizResponseServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/QuizResponse")
public class QuizResponseController {
    private final QuizResponseServiceImplementation quizResponseServiceImplementation;
    @PostMapping("/Insert")
    public void insert(@RequestParam("id") Integer id,  @RequestParam("questionIds") List<Integer> questionIds, @RequestBody List<QuizResponse> quizResponses)
    {
        quizResponseServiceImplementation.insert(id, questionIds, quizResponses);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestBody QuizResponse quizResponse)
    {
        quizResponseServiceImplementation.delete(quizResponse.getId());
    }

    @GetMapping("/Read")
    public void read(@RequestBody QuizResponse quizResponse)
    {
        System.out.println(quizResponseServiceImplementation.read(quizResponse.getId()));
    }

  /*  @PutMapping  ("/Update")
    public void update(@RequestBody QuizResponse quizResponse)
    {

        quizResponseServiceImplementation.updateById(quizResponse.getId());

    }*/

}
