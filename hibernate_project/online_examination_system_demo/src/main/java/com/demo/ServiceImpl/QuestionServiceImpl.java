package com.demo.ServiceImpl;

import java.util.List;

import com.demo.Dao.QuestionDao;
import com.demo.DaoImpl.QuestionDaoImpl;
import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Exam;
import com.demo.Service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

    QuestionDao questionDao = new QuestionDaoImpl();

    @Override
    public Question createQuestion(Question question) {
        // Invoke daoimpl method to save question object
        return questionDao.createQuestion(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        // Retrieve all questions
        return questionDao.getAllQuestions();
    }

    @Override
    public Question getQuestion(int questionId) {
        // Retrieve a specific question by its ID
        return questionDao.getQuestion(questionId);
    }

    @Override
    public List<Question> getQuestionsByExam(Exam exam) {
        // Retrieve questions associated with a specific exam
        return questionDao.getQuestionsByExam(exam);
    }

    @Override
    public Question updateQuestion(int questionId, Question updatedQuestion) {
        // Update an existing question
        return questionDao.updateQuestion(questionId, updatedQuestion);
    }

    @Override
    public String deleteQuestion(int questionId) {
        // Delete a question by its ID
        return questionDao.deleteQuestion(questionId);
    }
}
