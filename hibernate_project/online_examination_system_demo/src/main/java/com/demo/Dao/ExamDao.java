package com.demo.Dao;

import java.util.List;

import com.demo.examinationsystem.entities.Exam;

public interface ExamDao {
    Exam createExam(Exam exam);
    List<Exam> getAllExams();
    Exam getExamById(int examId);
    Exam updateExam(int examId, Exam updatedExam);
    String deleteExam(int examId);
}
