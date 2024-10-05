package com.demo.online_examination_system_demo;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_examination_system_demo.util.HibernateUtil;
import com.demo.examinationsystem.entities.Answer;
import com.demo.examinationsystem.entities.Exam;
import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Result;
import com.demo.examinationsystem.entities.User;
public class App {
	public static void main(String[] args) {
		// Obtain a Hibernate SessionFactory
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Create a new Student
		LocalDate date1 = LocalDate.of(1988, 1, 13);
		//Create User
/*		User user = new User(6, "sakshi", "sakshi@gmail.com", "sakskhi@123", "STUDENT");
		session.save(user);

		Exam exam = new Exam(1, "Computer Exam", "Computer related questions", 60, "Read and answer properly",1);
		session.save(exam);

		Question question = new Question(1, "What is the capital of France?", "A) Paris, B) London, C) Berlin, D) Madrid","A",1);
		session.save(question);

		Answer answer = new Answer(1, 1, "A", 1); // Assuming userId is 6
		session.save(answer);

		Result result = new Result(1, 85.5f, "Passed", 1,1,1); // Assuming a score of 85.5 and a passed status
		session.save(result);
*/
		// Open a new session
		Session session = factory.openSession();
		// Begin a transaction
		Transaction transaction = session.beginTransaction();
		// Save the student to the database
		
		// Commit the transaction
		transaction.commit();
		// Close the Session
		session.close();
		// Close the Session Factory
		factory.close();
	}
}
