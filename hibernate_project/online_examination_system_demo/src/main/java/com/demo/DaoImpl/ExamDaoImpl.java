package com.demo.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.Dao.ExamDao;
import com.demo.examinationsystem.entities.Exam;
import com.demo.exception.ResourceNotFoundException;
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class ExamDaoImpl implements ExamDao {

    @Override
    public Exam createExam(Exam exam) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(exam);
            transaction.commit();
            return exam;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating exam: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Exam> getAllExams() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Exam> query = session.createQuery("FROM Exam", Exam.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving exams: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Exam getExamById(int examId) {
        try (Session session = HibernateUtil.getSession()) {
            Exam exam = session.get(Exam.class, examId);
            if (exam == null) {
                throw new ResourceNotFoundException("Exam ID not found: " + examId);
            }
            return exam;
        } catch (HibernateException e) {
            System.err.println("Error retrieving exam: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Exam updateExam(int examId, Exam updatedExam) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, examId);
            if (exam != null) {
                exam.setTitle(updatedExam.getTitle());
                exam.setDescription(updatedExam.getDescription());
                exam.setDuration(updatedExam.getDuration());
                exam.setInstructions(updatedExam.getInstructions());
                session.update(exam); // Use update instead of saveOrUpdate
                transaction.commit();
                return exam;
            } else {
                throw new ResourceNotFoundException("Exam ID not found: " + examId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating exam: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteExam(int examId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, examId);
            if (exam != null) {
                session.delete(exam);
                transaction.commit();
                message = "Exam with ID " + examId + " is deleted";
            } else {
                throw new ResourceNotFoundException("Exam ID not found: " + examId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting exam: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
