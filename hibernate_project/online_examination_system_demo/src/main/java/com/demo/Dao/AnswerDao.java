package com.demo.Dao;

import java.util.List;

import com.demo.examinationsystem.entities.Answer;



public interface AnswerDao {

    // Create a new Answer
    Answer createAnswer(Answer answer);

    // Retrieve all Answers
    List<Answer> getAllAnswers();

    // Retrieve a specific Answer by its ID
    Answer getAnswer(int answerId);

    // Update an existing Answer
    Answer updateAnswer(int answerId, Answer updatedAnswer);

    // Delete an Answer by its ID
    String deleteAnswer(int answerId);

    // Get Answers by Question ID (optional, based on your use case)
    List<Answer> getAnswersByQuestionId(int questionId);
}
