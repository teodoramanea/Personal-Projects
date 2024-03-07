package com.example.SiteMatrimonial.Service.QuizQuestion;

import com.example.SiteMatrimonial.Model.QuizOption;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import com.example.SiteMatrimonial.Model.User;
import com.example.SiteMatrimonial.Repository.ChatRepo;
import com.example.SiteMatrimonial.Repository.QuizQuestionRepo;
import com.example.SiteMatrimonial.Repository.QuizResponseRepo;
import com.example.SiteMatrimonial.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizQuestionServiceImplementation implements QuizQuestionService{
    @Autowired
    private QuizQuestionRepo quizQuestionRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuizResponseRepo quizResponseRepo;

//    public QuizQuestionServiceImplementation(QuizResponseRepo quizResponseRepo) throws SQLException {
//        this.quizResponseRepo = quizResponseRepo;
//    }

    @Override
    public QuizQuestion findFirstById(Integer id) {
        return quizQuestionRepo.findFirstById(id);
    }

    @Override
    public void insert(QuizQuestion quizQuestion) {

            //userRepo.save(user);
            quizQuestionRepo.save(quizQuestion);

    }

    @Override
    public void delete(Integer id) {
        quizQuestionRepo.deleteById(id);
    }

    @Override
    public QuizQuestion read(Integer id) {
        return quizQuestionRepo.readById(id);
    }

    @Override
    public List<String> ReadAllQuestions() {
        List<QuizQuestion> quizQuestions = (List<QuizQuestion>) quizQuestionRepo.findAll();
        List<String> questionTexts = new ArrayList<>();

        for (QuizQuestion question : quizQuestions) {
            questionTexts.add(question.getQuestion());
        }

        return questionTexts;
    }

    @Override
    public List<QuizQuestion> ReadAll() {
        List<QuizQuestion> allQuestions = (List<QuizQuestion>) quizQuestionRepo.findAll();
        List<QuizQuestion> modifiedQuestions = new ArrayList<>();

        for (QuizQuestion question : allQuestions) {
            QuizQuestion modifiedQuestion = new QuizQuestion();
            modifiedQuestion.setId(question.getId());
            modifiedQuestion.setQuestion(question.getQuestion());

            modifiedQuestions.add(modifiedQuestion);
        }

        return modifiedQuestions;
    }

    @Override
    public QuizQuestion updateById(Integer questionId, QuizQuestion question) {
        QuizQuestion quizQuestion = quizQuestionRepo.findFirstById(questionId);
         quizQuestion.setQuestion(question.getQuestion());
         return quizQuestionRepo.save(quizQuestion);
    }


}
