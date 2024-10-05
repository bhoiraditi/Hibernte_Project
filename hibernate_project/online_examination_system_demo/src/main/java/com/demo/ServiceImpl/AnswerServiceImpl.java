package com.demo.ServiceImpl;

import java.util.List;

import com.demo.Dao.AnswerDao;
import com.demo.DaoImpl.AnswerDaoImpl;
import com.demo.examinationsystem.entities.Answer;
import com.demo.Service.AnswerService;

public class AnswerServiceImpl implements AnswerService {

    // Initialize the AnswerDao implementation
    AnswerDao answerDao = new AnswerDaoImpl();
    
    @Override
    public Answer createAnswer(Answer answer) {
        // Invoke DAO method to save answer object
        return answerDao.createAnswer(answer);
    }

    @Override
    public List<Answer> getAllAnswers() {
        // Retrieve all answers using DAO
        return answerDao.getAllAnswers();
    }

    @Override
    public Answer getAnswer(int answerId) {
        // Retrieve specific answer by ID using DAO
        return answerDao.getAnswer(answerId);
    }

    @Override
    public Answer updateAnswer(int answerId, Answer updatedAnswer) {
        // Update existing answer using DAO
        return answerDao.updateAnswer(answerId, updatedAnswer);
    }

    @Override
    public String deleteAnswer(int answerId) {
        // Delete answer by ID using DAO
        return answerDao.deleteAnswer(answerId);
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        // Retrieve answers associated with a specific question using DAO
        return answerDao.getAnswersByQuestionId(questionId);
    }
}
