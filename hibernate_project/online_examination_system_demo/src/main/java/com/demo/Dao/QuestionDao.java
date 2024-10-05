package com.demo.Dao;

import java.util.List;

import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Exam;

public interface QuestionDao {

    Question createQuestion(Question question);
    
    List<Question> getAllQuestions();
    
    Question getQuestion(int questionId);
    
    List<Question> getQuestionsByExam(Exam exam);
    
    Question updateQuestion(int questionId, Question updatedQuestion);
    
    String deleteQuestion(int questionId);
}
