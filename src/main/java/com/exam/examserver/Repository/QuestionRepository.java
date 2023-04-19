package com.exam.examserver.Repository;

import com.exam.examserver.Entity.exam.Question;
import com.exam.examserver.Entity.exam.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
