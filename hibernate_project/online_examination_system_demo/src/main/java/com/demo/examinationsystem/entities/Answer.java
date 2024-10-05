package com.demo.examinationsystem.entities;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @Column(name = "AnswerId", length = 10)
    private int answerId; // Manually set the answerId

    @Column(name = "UserId", length = 60)
    private Integer userId; // Represents userId

    @Column(name = "AnswerText", length = 50)
    private String answerText;

    @ManyToOne
    @JoinColumn(name="QuestionId")
    private Question questionId;

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Question getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Question questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", userId=" + userId + ", answerText=" + answerText + ", questionId="
				+ questionId + "]";
	}

	public Answer(int answerId, Integer userId, String answerText, Question questionId) {
		super();
		this.answerId = answerId;
		this.userId = userId;
		this.answerText = answerText;
		this.questionId = questionId;
	}

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

    }
