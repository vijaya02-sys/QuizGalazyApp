package com.quizzer.quizgalaxyapp.dao;

import com.quizzer.quizgalaxyapp.model.Questiondb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questiondb, Integer>
{
    List<Questiondb> findByCategory(String category);

    @Query(value = "SELECT * FROM questiondb q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Questiondb> findRandomQuestionsByCategory(String category, int numQ);

}
