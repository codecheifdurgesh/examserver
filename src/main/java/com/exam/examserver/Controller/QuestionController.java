package com.exam.examserver.Controller;

import com.exam.examserver.Entity.exam.Question;
import com.exam.examserver.Entity.exam.Quiz;
import com.exam.examserver.Entity.exam.Result;
import com.exam.examserver.Service.QuestionService;
import com.exam.examserver.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return  ResponseEntity.ok(this.questionService.addQuestion(question));
    }
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return  ResponseEntity.ok(this.questionService.updateQuestion(question));
    }
    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qId") Long qId){
//        Quiz quiz=new Quiz();
//        quiz.setqId(qId);
//        Set<Question> questionSet= this.questionService.getQuestionsOfQuiz(quiz);
//
//        return ResponseEntity.ok(questionSet);
        Quiz quiz=this.quizService.getQuiz(qId);
        Set<Question> questions=quiz.getQuestions();
        List<Question> list=new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        list.forEach((quest)->{
                quest.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/quiz/admin/{qId}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qId") Long qId){
        Quiz quiz=new Quiz();
        quiz.setqId(qId);
        Set<Question> questionSet= this.questionService.getQuestionsOfQuiz(quiz);

        return ResponseEntity.ok(questionSet);


    }
    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable("questionId") Long questionId){
        return this.questionService.getQuestion(questionId);
    }
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId){
        this.questionService.deleteQuestion(questionId);
    }
    @PostMapping("/evalquiz")
    public Result evalQuiz(@RequestBody List<Question> questions){
        double marksGot=0;
        int correctAnsers=0;
        int attempted=0;

        for(Question q:questions){
            Question question=new Question();
            question=this.questionService.getQuestion(q.getQuesId());
            double markSingle=Double.parseDouble(q.getQuiz().getMaxMarks())/questions.size();
            if(question.getAnswer().equals(q.getGivenAnswer())){
                correctAnsers++;
                marksGot+=markSingle;
            }
            if(q.getGivenAnswer()!=null){
                attempted++;
            }

        }

        Result result=new Result(marksGot,correctAnsers,attempted);
        return result;

    }

}
