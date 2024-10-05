package com.demo.online_examination_system_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.demo.examinationsystem.entities.Exam;
import com.demo.examinationsystem.entities.Result;
import com.demo.examinationsystem.entities.User;
import com.demo.online_examination_system_demo.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserOperation {
    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = factory.openSession();
            // Begin a transaction
            transaction = session.beginTransaction();

            // Create a list to hold exams for the user (initially empty)
            List<Exam> exams = new ArrayList<>();
            List<Result> results = new ArrayList<>();

            // Create a new User object
            User user = new User(6, "sakshi", "sakshi@gmail.com", "sakskhi@123", "STUDENT");

            // Save the user to the database
            session.save(user);

            // Commit the transaction
            transaction.commit();

            // Retrieve the user from the database
            User retrievedUser = session.get(User.class, user.getUserId());
            // Display the retrieved user
            System.out.println(retrievedUser);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
            factory.close();
        }
    }
}
