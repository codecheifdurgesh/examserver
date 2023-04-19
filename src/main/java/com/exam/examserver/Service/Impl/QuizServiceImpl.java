package com.exam.examserver.Service.Impl;

import com.exam.examserver.Entity.exam.Category;
import com.exam.examserver.Entity.exam.Quiz;
import com.exam.examserver.Repository.QuizRepository;
import com.exam.examserver.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private EntityManager entityManager;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new LinkedHashSet<>((Collection) this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {

        Quiz quiz=new Quiz();
        quiz=this.quizRepository.findById(quizId).get();
        this.quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizzesByCategory(Category category) {
        return this.quizRepository.findBycategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByactive(true);
    }

    @Override
    public List<Quiz> findActiveQuizzesByCategory(Category category) {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        return quizRepository.findByCategoryAndActiveTrue(category);
    }
}
