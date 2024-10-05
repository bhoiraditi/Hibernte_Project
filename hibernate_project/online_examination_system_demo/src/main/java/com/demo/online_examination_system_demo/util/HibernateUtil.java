package com.demo.online_examination_system_demo.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.examinationsystem.entities.Answer;
import com.demo.examinationsystem.entities.Exam;
import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Result;
import com.demo.examinationsystem.entities.User;



public class HibernateUtil {
	
	private final static SessionFactory sessionFactory=buildSessionFactory();
private static SessionFactory buildSessionFactory()
{

	try {
		return new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Exam.class)
				.addAnnotatedClass(Question.class)
				.addAnnotatedClass(Answer.class)
				.addAnnotatedClass(Result.class)
				.buildSessionFactory();
		
	}catch (Throwable e) {
		throw new ExceptionInInitializerError(e);
	}
}

public static SessionFactory getSessionFactory() {
	return sessionFactory;
}

public static Session getSession()
{
  return getSessionFactory().openSession(); //session opened
}
	
}
