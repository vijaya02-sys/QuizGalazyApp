package com.quizzer.quizgalaxyapp.dao;

import com.quizzer.quizgalaxyapp.model.Quizdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quizdb, Integer>
{

}
