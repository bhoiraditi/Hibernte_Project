package com.demo.online_examination_system_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Exam; // Import the Exam class
import com.demo.online_examination_system_demo.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class QuestionOperation {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = factory.openSession();
            // Begin a transaction
            transaction = session.beginTransaction();

            // Create an Exam object (make sure it exists in the database)
            Exam exam = session.get(Exam.class, 1); // Assuming exam with ID 5 exists

            // Create a Question object
            Question question = new Question(1, "What is the capital of France?", 
                                              "A) Paris, B) London, C) Berlin, D) Madrid", 
                                              "A", exam);

            // Save the question to the database
            session.save(question);

            // Commit the transaction
            transaction.commit();

            // Retrieve the question from the database
            Question retrievedQuestion = session.get(Question.class, question.getQuestionId());
            // Display the retrieved question
            System.out.println(retrievedQuestion);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
        }
        // Do not close factory here; manage it at application shutdown
    }
}
