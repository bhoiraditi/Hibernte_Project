package com.demo.online_examination_system_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.demo.examinationsystem.entities.Result;
import com.demo.examinationsystem.entities.User; // Import the User class
import com.demo.examinationsystem.entities.Exam; // Import the Exam class
import com.demo.examinationsystem.entities.Answer; // Import the Answer class
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class ResultOperation {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = factory.openSession();
            // Begin a transaction
            transaction = session.beginTransaction();

            // Create or retrieve a User object (make sure it exists in the database)
            User user = session.get(User.class, 1); // Assuming user with ID 5 exists

            // Create or retrieve an Exam object (make sure it exists in the database)
            Exam exam = session.get(Exam.class, 1); // Assuming exam with ID 5 exists

            // Create or retrieve an Answer object (make sure it exists in the database)
            Answer answer = session.get(Answer.class, 1); // Assuming answer with ID 1 exists

            // Create a Result object
            Result result = new Result(1, 85.5f, "Passed", user, exam, answer); // Assuming a score of 85.5 and a passed status

            // Save the result to the database
            session.save(result);

            // Commit the transaction
            transaction.commit();

            // Retrieve the result from the database
            Result retrievedResult = session.get(Result.class, result.getResultId());
            // Display the retrieved result
            System.out.println(retrievedResult);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
        }
        // Do not close factory here; manage it at application shutdown
    }
}
