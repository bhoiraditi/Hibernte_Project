package com.demo.online_examination_system_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.demo.examinationsystem.entities.Exam;
import com.demo.examinationsystem.entities.User;
import com.demo.online_examination_system_demo.util.HibernateUtil;


public class ExamOperation {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            // Create or retrieve the User object (make sure it exists in the database)
            User user = new User(1, "honey", "honey@gmail.com", "honey@123", "STUDENT");
            session.save(user); // Ensure user is saved if not already

            // Create an Exam object and associate the user with it
            Exam exam = new Exam(1, "Computer Exam", "Computer related questions", 60, "Read and answer properly", user);

            // Save the exam to the database
            session.save(exam);

            transaction.commit();

            // Retrieve the exam from the database
            Exam retrievedExam = session.get(Exam.class, exam.getExamId());
            System.out.println(retrievedExam);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        // Manage factory at application shutdown
    }
}
