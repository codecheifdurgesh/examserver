package com.exam.examserver.Controller;

import com.exam.examserver.Entity.exam.Category;
import com.exam.examserver.Entity.exam.Quiz;
import com.exam.examserver.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1=this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        Quiz quiz1=this.quizService.updateQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }
    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") Long quizId){
        return this.quizService.getQuiz(quizId);
    }
    @DeleteMapping("/{qId}")
    public void deleteQuiz(@PathVariable("qId") Long qId){
        this.quizService.deleteQuiz(qId);
    }
    @GetMapping("/category/{cId}")
    public List<Quiz> getQuizzesByCategory(@PathVariable("cId") Long cId){
        Category category=new Category();
        category.setcId(cId);
        return this.quizService.getQuizzesByCategory(category);

    }

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }
    @GetMapping("/category/active/{cId}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cId") Long cId){
        Category category=new Category();
        category.setcId(cId);
        return this.quizService.findActiveQuizzesByCategory(category);
    }
}
