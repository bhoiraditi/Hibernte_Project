package com.demo.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.Dao.AnswerDao;
import com.demo.examinationsystem.entities.Answer;
import com.demo.exception.ResourceNotFoundException;
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class AnswerDaoImpl implements AnswerDao {

    @Override
    public Answer createAnswer(Answer answer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(answer);
            transaction.commit();
            return answer;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating answer: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Answer> getAllAnswers() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Answer> query = session.createQuery("FROM Answer", Answer.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving answers: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Answer getAnswer(int answerId) {
        try (Session session = HibernateUtil.getSession()) {
            Answer answer = session.get(Answer.class, answerId);
            if (answer == null) {
                throw new ResourceNotFoundException("Answer ID not found: " + answerId);
            }
            return answer;
        } catch (HibernateException e) {
            System.err.println("Error retrieving answer: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Answer updateAnswer(int answerId, Answer updatedAnswer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Answer answer = session.get(Answer.class, answerId);
            if (answer != null) {
                answer.setUserId(updatedAnswer.getUserId());
                answer.setAnswerText(updatedAnswer.getAnswerText());
                answer.setQuestionId(updatedAnswer.getQuestionId());
                session.update(answer); // Use update instead of saveOrUpdate for clarity
                transaction.commit();
                return answer;
            } else {
                throw new ResourceNotFoundException("Answer ID not found: " + answerId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating answer: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteAnswer(int answerId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Answer answer = session.get(Answer.class, answerId);
            if (answer != null) {
                session.delete(answer);
                transaction.commit();
                message = "Answer deleted successfully";
            } else {
                message = "Answer ID not found: " + answerId;
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting answer: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Answer> query = session.createQuery("FROM Answer WHERE question.id = :questionId", Answer.class);
            query.setParameter("questionId", questionId);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving answers by question ID: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }
}
