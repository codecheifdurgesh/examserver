package com.exam.examserver.Repository;

import com.exam.examserver.Entity.exam.Category;
import com.exam.examserver.Entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByactive(boolean b);

    List<Quiz> findByCategoryAndActiveTrue(Category category);
}
