package com.demo.online_examination_system_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.demo.examinationsystem.entities.Answer;
import com.demo.examinationsystem.entities.Question; // Import the Question class
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class AnswerOperation {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = factory.openSession();
            // Begin a transaction
            transaction = session.beginTransaction();

            // Create a Question object (make sure it exists in the database)
            Question question = session.get(Question.class, 1); // Assuming question with ID 1 exists

            // Create an Answer object
            Answer answer = new Answer(1, 1, "A", question); // Assuming userId is 5

            // Save the answer to the database
            session.save(answer);

            // Commit the transaction
            transaction.commit();

            // Retrieve the answer from the database
            Answer retrievedAnswer = session.get(Answer.class, answer.getAnswerId());
            // Display the retrieved answer
            System.out.println(retrievedAnswer);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
        }
        // Do not close factory here; manage it at application shutdown
    }
}
