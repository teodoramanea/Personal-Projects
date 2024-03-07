package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.QuizOption;
import com.example.SiteMatrimonial.Service.QuizOption.QuizOptionServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/QuizOption")
public class QuizOptionController {
    private final QuizOptionServiceImplementation quizOptionServiceImplementation;
    @PostMapping("/Insert")
    public void insert(@RequestParam("id") Integer id, @RequestBody QuizOption quizOption)
    {
        quizOptionServiceImplementation.insert(id, quizOption);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam("id") Integer id)
    {
        quizOptionServiceImplementation.delete(id);
    }

    @GetMapping("/Read")
    public void read(@RequestBody  QuizOption quizOption)
    {
        System.out.println(quizOptionServiceImplementation.read(quizOption.getId()));
    }

    @GetMapping("/ReadAll")
    public List<String> ReadAll(){
        return  quizOptionServiceImplementation.ReadAllOptions();
    }

    @GetMapping("/ReadAlll")
    public List<QuizOption> ReadAlll(){
        return  quizOptionServiceImplementation.ReadAll();
    }

    @PutMapping  ("/Update")
    public void update(@RequestParam("id") Integer id, @RequestBody  QuizOption quizOption)
    {

        quizOptionServiceImplementation.updateById(id, quizOption);

    }
}
