package com.demo.examinationsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Question {

    @Id
    @Column(name = "QuestionId", length = 10)
    private int questionId;

    @Column(name = "QuestionText", length = 70)
    private String questionText;

    @Column(name = "Options", length = 200)
    private String options;

    @Column(name = "CurrectAnswer", length = 25)
    private String currectAnswer;
    
    @ManyToOne
    @JoinColumn(name="ExamId")
    private Exam examId;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getCurrectAnswer() {
		return currectAnswer;
	}

	public void setCurrectAnswer(String currectAnswer) {
		this.currectAnswer = currectAnswer;
	}

	public Exam getExamId() {
		return examId;
	}

	public void setExamId(Exam examId) {
		this.examId = examId;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionText=" + questionText + ", options=" + options
				+ ", currectAnswer=" + currectAnswer + ", examId=" + examId + "]";
	}

	public Question(int questionId, String questionText, String options, String currectAnswer, Exam examId) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
		this.options = options;
		this.currectAnswer = currectAnswer;
		this.examId = examId;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
