package com.example.SiteMatrimonial.Service.QuizOption;

import com.example.SiteMatrimonial.Model.QuizOption;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.example.SiteMatrimonial.Repository.QuizOptionRepo;
import com.example.SiteMatrimonial.Repository.QuizQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizOptionServiceImplementation implements QuizOptionService{
    @Autowired
    private QuizOptionRepo quizOptionRepo;

    @Autowired
    private QuizQuestionRepo quizQuestionRepo;

    public QuizOptionServiceImplementation() throws SQLException {
    }

    @Override
    public QuizOption findFirstById(Integer id) {
        return quizOptionRepo.findFirstById(id);
    }

    @Override
    public void insert(Integer id, QuizOption quizOption) {
        QuizQuestion quizQuestion = quizQuestionRepo.findFirstById(id);
        if (quizQuestion != null) {
            quizOption.setQuizQuestion(quizQuestion);

            List<QuizOption> answerOptionsList = quizQuestion.getAnswerOptionsList();
            answerOptionsList.add(quizOption);
            quizQuestion.setAnswerOptionsList(answerOptionsList);

            quizQuestionRepo.save(quizQuestion);
            quizOptionRepo.save(quizOption);

            System.out.println("Numărul de opțiuni: " + quizQuestion.getAnswerOptionsList().size());
            for (QuizOption option : quizQuestion.getAnswerOptionsList()) {
                System.out.println(option.getAnswerOption());
            }
        }

    }
        @Override
    public void delete(Integer id) {
        quizOptionRepo.deleteById(id);
    }

    @Override
    public QuizOption read(Integer id) {
        return quizOptionRepo.readById(id);
    }

    @Override
    public List<String> ReadAllOptions() {
        List<QuizOption> quizOptions = (List<QuizOption>) quizOptionRepo.findAll();
        List<String> optionsTexts = new ArrayList<>();

        for (QuizOption option : quizOptions) {
            optionsTexts.add(option.getAnswerOption());
        }

        return optionsTexts;
    }

    @Override
    public List<QuizOption> ReadAll() {
        List<QuizOption> allOptions = (List<QuizOption>) quizOptionRepo.findAll();
        List<QuizOption> modifiedOptions = new ArrayList<>();

        for (QuizOption option : allOptions) {
            QuizOption modifiedOption = new QuizOption();
            modifiedOption.setId(option.getId());
            modifiedOption.setAnswerOption(option.getAnswerOption());
            modifiedOption.setQuizQuestion(option.getQuizQuestion());
            modifiedOption.getQuizQuestion().setQuizResponseList(null);
            modifiedOption.getQuizQuestion().setAnswerOptionsList(null);
            modifiedOptions.add(modifiedOption);
        }

        return modifiedOptions;
    }

    @Override
    public QuizOption updateById(Integer id, QuizOption option) {
        QuizOption quizOption = quizOptionRepo.findFirstById(id);
        quizOption.setAnswerOption(option.getAnswerOption());
        return quizOptionRepo.save(quizOption);
    }


}

