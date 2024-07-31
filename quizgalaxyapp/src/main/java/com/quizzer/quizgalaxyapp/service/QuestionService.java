package com.quizzer.quizgalaxyapp.service;

import com.quizzer.quizgalaxyapp.model.Questiondb;
import com.quizzer.quizgalaxyapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Questiondb>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questiondb>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questiondb question) {
        questionDao.save(question);
        try {
            return new ResponseEntity<>("success!", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>("failure!", HttpStatus.BAD_REQUEST);

    }
}
