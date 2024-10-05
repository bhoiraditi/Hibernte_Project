package com.demo.Service;

import java.util.List;

import com.demo.examinationsystem.entities.Exam;

public interface ExamService {
    Exam createExam(Exam exam);    
    List<Exam> getAllExams();
    Exam getExamById(int examId);
    Exam updateExam(int examId, Exam updatedExam);
    String deleteExam(int examId);
}
