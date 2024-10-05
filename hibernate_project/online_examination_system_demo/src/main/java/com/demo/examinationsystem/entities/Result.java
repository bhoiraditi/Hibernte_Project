package com.demo.examinationsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Result {

	@Id
	@Column(name = "ResultId", length = 10)
	private int resultId;


	@Column(name = "Score")
	private float score;

	@Column(name = "Status")
	private String status;

	
	@ManyToOne
	@JoinColumn(name="UserId")
	private User userId;
	

	@ManyToOne
	@JoinColumn(name="ExamId")
	private Exam examId;
	

	@OneToOne
	@JoinColumn(name = "answerId")
	private Answer answer;


	public int getResultId() {
		return resultId;
	}


	public void setResultId(int resultId) {
		this.resultId = resultId;
	}


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public Exam getExamId() {
		return examId;
	}


	public void setExamId(Exam examId) {
		this.examId = examId;
	}


	public Answer getAnswer() {
		return answer;
	}


	public void setAnswer(Answer answer) {
		this.answer = answer;
	}


	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", score=" + score + ", status=" + status + ", userId=" + userId
				+ ", examId=" + examId + ", answer=" + answer + "]";
	}


	public Result(int resultId, float score, String status, User userId, Exam examId, Answer answer) {
		super();
		this.resultId = resultId;
		this.score = score;
		this.status = status;
		this.userId = userId;
		this.examId = examId;
		this.answer = answer;
	}


	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

}
