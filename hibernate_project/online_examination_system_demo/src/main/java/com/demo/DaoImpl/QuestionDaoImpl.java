package com.demo.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.Dao.QuestionDao;
import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Exam;
import com.demo.exception.ResourceNotFoundException;
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class QuestionDaoImpl implements QuestionDao {

    @Override
    public Question createQuestion(Question question) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(question);
            transaction.commit();
            return question;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating question: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestions() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Question> query = session.createQuery("FROM Question", Question.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving questions: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Question getQuestion(int questionId) {
        try (Session session = HibernateUtil.getSession()) {
            Question question = session.get(Question.class, questionId);
            if (question == null) {
                throw new ResourceNotFoundException("Question ID not found: " + questionId);
            }
            return question;
        } catch (HibernateException e) {
            System.err.println("Error retrieving question: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Question> getQuestionsByExam(Exam exam) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Question> query = session.createQuery("FROM Question WHERE exam = :exam", Question.class);
            query.setParameter("exam", exam);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving questions by exam: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Question updateQuestion(int questionId, Question updatedQuestion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Question question = session.get(Question.class, questionId);
            if (question != null) {
                question.setQuestionText(updatedQuestion.getQuestionText());
                question.setOptions(updatedQuestion.getOptions());
                question.setCurrectAnswer(updatedQuestion.getCurrectAnswer());
                question.setExamId(updatedQuestion.getExamId());
                session.update(question); // More explicit than saveOrUpdate
                transaction.commit();
                return question;
            } else {
                throw new ResourceNotFoundException("Question ID not found: " + questionId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating question: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteQuestion(int questionId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Question question = session.get(Question.class, questionId);
            if (question != null) {
                session.delete(question);
                transaction.commit();
                message = "Question deleted successfully";
            } else {
                throw new ResourceNotFoundException("Question ID not found: " + questionId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting question: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
