package com.demo.ServiceImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.demo.Dao.ExamDao;
import com.demo.DaoImpl.ExamDaoImpl;
import com.demo.examinationsystem.entities.Exam;
import com.demo.online_examination_system_demo.util.HibernateUtil;
import com.demo.Service.ExamService;

public class ExamServiceImpl implements ExamService {

    ExamDao examDao = new ExamDaoImpl();

    @Override
    public Exam createExam(Exam exam) {
        // Invoke dao method to save the exam object
        return examDao.createExam(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        // Retrieve all exams
        return examDao.getAllExams();
    }

    @Override
    public Exam updateExam(int examId, Exam updatedExam) {
        // Update an existing exam
        return examDao.updateExam(examId, updatedExam);
    }

    @Override
    public String deleteExam(int examId) {
        // Delete an exam by ID
        return examDao.deleteExam(examId);
    }

    public Exam getExamById(int examId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Exam exam = session.get(Exam.class, examId);
        System.out.println("Exam ID queried: " + examId);
        System.out.println("Retrieved Exam: " + exam);
        session.close();
        return exam;
    }

}
