package com.quizzer.quizgalaxyapp.service;

import com.quizzer.quizgalaxyapp.dao.QuestionDao;
import com.quizzer.quizgalaxyapp.dao.QuizDao;
import com.quizzer.quizgalaxyapp.model.QuestionWrapper;
import com.quizzer.quizgalaxyapp.model.Questiondb;
import com.quizzer.quizgalaxyapp.model.Quizdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quizzer.quizgalaxyapp.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questiondb> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quizdb quiz = new Quizdb();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("created!", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
    {
        Optional<Quizdb> quiz = quizDao.findById(id);
        List<Questiondb> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Questiondb question : questionsFromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
    {
        Quizdb quiz = quizDao.findById(id).get();
        List<Questiondb> questionsFromDB = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response : responses)
        {
            System.out.println(questionsFromDB.get(i).getRightAnswer() + " -> " + response.getResponse());
            if(response.getResponse().equals(questionsFromDB.get(i).getRightAnswer()))
            {
                right++;
                System.out.println(right);
            }

            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
