package com.exam.examserver.Service;

import com.exam.examserver.Entity.exam.Category;
import com.exam.examserver.Entity.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public  Quiz updateQuiz(Quiz quiz);
    public Set<Quiz> getQuizzes();
    public Quiz getQuiz(Long quizId);
    public void deleteQuiz(Long quizId);

    List<Quiz> getQuizzesByCategory(Category category);

    List<Quiz> getActiveQuizzes();
    List<Quiz> findActiveQuizzesByCategory(Category c);
}
