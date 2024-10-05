package com.demo.examinationsystem.entities;

import javax.persistence.*;

@Entity
public class Exam {
    @Id
 
    private int examId;
    private String title;
    private String description;
    private int duration; // in minutes
    private String instructions;

    @ManyToOne // Assuming each exam is associated with one user
    @JoinColumn(name = "user_id") // Foreign key column name in the Exam table
    private User user;

    // Default constructor
    public Exam() {}

    // Constructor with parameters
    public Exam(int examId, String title, String description, int duration, String instructions, User user) {
        this.examId = examId;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.instructions = instructions;
        this.user = user;
    }

    // Getters and setters
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "Exam [examId=" + examId + ", title=" + title + ", description=" + description + ", duration=" + duration
				+ ", instructions=" + instructions + ", user=" + user + "]";
	}
    
}
