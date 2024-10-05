package com.exam;

import com.demo.Service.UserService;
import com.demo.Service.ExamService;
import com.demo.Service.QuestionService;
import com.demo.Service.AnswerService;
import com.demo.Service.ResultService;
import com.demo.ServiceImpl.UserServiceImpl;
import com.demo.ServiceImpl.ExamServiceImpl;
import com.demo.ServiceImpl.QuestionServiceImpl;
import com.demo.ServiceImpl.AnswerServiceImpl;
import com.demo.ServiceImpl.ResultServiceImpl;
import com.demo.examinationsystem.entities.User;
import com.demo.examinationsystem.entities.Exam;
import com.demo.examinationsystem.entities.Question;
import com.demo.examinationsystem.entities.Answer;
import com.demo.examinationsystem.entities.Result;
import com.demo.exception.ResourceNotFoundException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NewAllOperation {

    static UserService userService = new UserServiceImpl();
    static ExamService examService = new ExamServiceImpl();
    static QuestionService questionService = new QuestionServiceImpl();
    static AnswerService answerService = new AnswerServiceImpl();
    static ResultService resultService = new ResultServiceImpl();

    static Scanner sc = new Scanner(System.in);

    public static User userInputs() {
        sc.nextLine(); // Clear the buffer
        System.out.println("Enter User ID:");
        int userId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter User Name:");
        String userName = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        System.out.println("Enter Password:");
        String password = sc.nextLine();

        System.out.println("Enter Role:");
        String role = sc.nextLine();

        return new User(userId, userName, email, password, role);
    }

    

    public static Exam examInputs() {
        System.out.println("Enter Exam ID:");
        int examId = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter Title:");
        String title = sc.nextLine();

        System.out.println("Enter Description:");
        String description = sc.nextLine();

        System.out.println("Enter Duration (in minutes):");
        int duration = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.println("Enter Instructions:");
        String instructions = sc.nextLine();

        // Get User ID
        System.out.println("Enter User ID (the owner of the exam):");
        int userId = sc.nextInt();
        sc.nextLine();

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("User ID not found. Please try again.");
            return null;
        }

        // Create a list with the single user
        List<User> userList = Arrays.asList(user); // Use Arrays.asList to create a list with the user

        return new Exam(examId, title, description, duration, instructions, user);
    }

    public static Question questionInputs() {
        sc.nextLine();
        System.out.println("Enter Question ID:");
        int questionId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Question Text:");
        String questionText = sc.nextLine();

        System.out.println("Enter Options (comma-separated):");
        String options = sc.nextLine();

        System.out.println("Enter Correct Answer:");
        String correctAnswer = sc.nextLine();

        // Get Exam ID
        System.out.println("Enter Exam ID (the associated exam):");
        int examId = sc.nextInt();
        sc.nextLine();

        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            System.out.println("Exam ID not found. Please try again.");
            return null;
        }

        return new Question(questionId, questionText, options, correctAnswer, exam);
    }

    public static Answer answerInputs() {
        sc.nextLine();
        System.out.println("Enter Answer ID:");
        int answerId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter User ID:");
        int userId = sc.nextInt();
        sc.nextLine();

        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("User ID not found. Please try again.");
            return null;
        }

        System.out.println("Enter Answer Text:");
        String answerText = sc.nextLine();

        // Get Question ID
        System.out.println("Enter Question ID (related to the answer):");
        int questionId = sc.nextInt();
        sc.nextLine();

        Question question = questionService.getQuestion(questionId);
        if (question == null) {
            System.out.println("Question ID not found. Please try again.");
            return null;
        }

        return new Answer(answerId, userId, answerText, question);
    }

    public static Result resultInputs() {
        try {
            System.out.print("Enter Result ID: ");
            int resultId = sc.nextInt();
            sc.nextLine(); // Clear buffer

            System.out.print("Enter Score: ");
            float score = (float) sc.nextDouble(); // Changed to Double
            sc.nextLine(); // Clear buffer

            System.out.print("Enter Status: ");
            String status = sc.nextLine();

            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();
            sc.nextLine(); // Clear buffer

            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("User ID not found. Please try again.");
                return null;
            }

            System.out.print("Enter Exam ID: ");
            int examId = sc.nextInt();
            sc.nextLine(); // Clear buffer

            Exam exam = examService.getExamById(examId);
            if (exam == null) {
                System.out.println("Exam ID not found. Please try again.");
                return null;
            }

            System.out.print("Enter Answer ID (if applicable, else enter 0): ");
            int answerId = sc.nextInt();
            sc.nextLine(); // Clear buffer

            Answer answer = null;
            if (answerId != 0) {
                answer = answerService.getAnswer(answerId);
                if (answer == null) {
                    System.out.println("Answer ID not found. Please try again.");
                    return null;
                }
            }

            return new Result(resultId, score, status, user, exam, answer);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct type.");
            sc.nextLine(); // Clear the invalid input
            return null;
        }
    }

    public static void userOperations() {
        while (true) {
            System.out.println("Press 1. Add User\nPress 2. Retrieve All Users\n" +
                               "Press 3. Update User\nPress 4. Delete User\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    User user = userInputs();
                    if (user != null) {
                        User savedUser = userService.createUser(user);
                        System.out.println("User has been saved successfully: " + savedUser);
                    }
                    break;

                case 2:
                    List<User> users = userService.getAllUsers();
                    for (User u : users) {
                        System.out.println(u);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter User ID to update the information:");
                    int userId = sc.nextInt();
                    User existingUser = userService.getUserById(userId);
                    if (existingUser != null) {
                        User updatedUser = userInputs();
                        if (updatedUser != null) {
                            User updatedInfo = userService.updateUser(userId, updatedUser);
                            System.out.println("User has been updated successfully: " + updatedInfo);
                        }
                    } else {
                        System.out.println("User ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter User ID to delete the information:");
                    int id = sc.nextInt();
                    String message = userService.deleteUser(id);
                    System.out.println(message);
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void examOperations() {
        while (true) {
            System.out.println("Press 1. Add Exam\nPress 2. Retrieve All Exams\n" +
                               "Press 3. Update Exam\nPress 4. Delete Exam\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Exam exam = examInputs();
                    if (exam != null) {
                        Exam savedExam = examService.createExam(exam);
                        System.out.println("Exam has been saved successfully: " + savedExam);
                    }
                    break;

                case 2:
                    List<Exam> exams = examService.getAllExams();
                    for (Exam ex : exams) {
                        System.out.println(ex);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Exam ID to update the information:");
                    int examId = sc.nextInt();
                    Exam existingExam = examService.getExamById(examId);
                    if (existingExam != null) {
                        Exam updatedExam = examInputs();
                        if (updatedExam != null) {
                            Exam updatedInfo = examService.updateExam(examId, updatedExam);
                            System.out.println("Exam data has been updated successfully: " + updatedInfo);
                        }
                    } else {
                        System.out.println("Exam ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Exam ID to delete the information:");
                    int id = sc.nextInt();
                    String message = examService.deleteExam(id);
                    System.out.println(message);
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void questionOperations() {
        while (true) {
            System.out.println("Press 1. Add Question\nPress 2. Retrieve All Questions\n" +
                               "Press 3. Update Question\nPress 4. Delete Question\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Question question = questionInputs();
                    if (question != null) {
                        Question savedQuestion = questionService.createQuestion(question);
                        System.out.println("Question has been saved successfully: " + savedQuestion);
                    }
                    break;

                case 2:
                    List<Question> questions = questionService.getAllQuestions();
                    for (Question q : questions) {
                        System.out.println(q);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Question ID to update the information:");
                    int questionId = sc.nextInt();
                    Question existingQuestion = questionService.getQuestion(questionId);
                    if (existingQuestion != null) {
                        Question updatedQuestion = questionInputs();
                        if (updatedQuestion != null) {
                            Question updatedInfo = questionService.updateQuestion(questionId, updatedQuestion);
                            System.out.println("Question has been updated successfully: " + updatedInfo);
                        }
                    } else {
                        System.out.println("Question ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Question ID to delete the information:");
                    int id = sc.nextInt();
                    String message = questionService.deleteQuestion(id);
                    System.out.println(message);
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void answerOperations() {
        while (true) {
            System.out.println("Press 1. Add Answer\nPress 2. Retrieve All Answers\n" +
                               "Press 3. Update Answer\nPress 4. Delete Answer\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Answer answer = answerInputs();
                    if (answer != null) {
                        Answer savedAnswer = answerService.createAnswer(answer);
                        System.out.println("Answer has been saved successfully: " + savedAnswer);
                    }
                    break;

                case 2:
                    List<Answer> answers = answerService.getAllAnswers();
                    for (Answer a : answers) {
                        System.out.println(a);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Answer ID to update the information:");
                    int answerId = sc.nextInt();
                    Answer existingAnswer = answerService.getAnswer(answerId);
                    if (existingAnswer != null) {
                        Answer updatedAnswer = answerInputs();
                        if (updatedAnswer != null) {
                            Answer updatedInfo = answerService.updateAnswer(answerId, updatedAnswer);
                            System.out.println("Answer has been updated successfully: " + updatedInfo);
                        }
                    } else {
                        System.out.println("Answer ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Answer ID to delete the information:");
                    int id = sc.nextInt();
                    String message = answerService.deleteAnswer(id);
                    System.out.println(message);
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void resultOperations() {
        while (true) {
            System.out.println("Press 1. Add Result\nPress 2. Retrieve All Results\n" +
                               "Press 3. Update Result\nPress 4. Delete Result\n" +
                               "Press 5. To get back to the main menu");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    Result result = resultInputs();
                    if (result != null) {
                        Result savedResult = resultService.createResult(result);
                        System.out.println("Result has been saved successfully: " + savedResult);
                    }
                    break;

                case 2:
                    List<Result> results = resultService.getAllResults();
                    for (Result r : results) {
                        System.out.println(r);
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter Result ID to update the information:");
                    int resultId = sc.nextInt();
                    Result existingResult = resultService.getResult(resultId);
                    if (existingResult != null) {
                        Result updatedResult = resultInputs();
                        if (updatedResult != null) {
                            Result updatedInfo = resultService.updateResult(resultId, updatedResult);
                            System.out.println("Result has been updated successfully: " + updatedInfo);
                        }
                    } else {
                        System.out.println("Result ID not found");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Enter Result ID to delete the information:");
                    int id = sc.nextInt();
                    String message = resultService.deleteResult(id);
                    System.out.println(message);
                    break;

                case 5:
                    return; // Return to main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an operation:\n" +
                               "1. User Operations\n" +
                               "2. Exam Operations\n" +
                               "3. Question Operations\n" +
                               "4. Answer Operations\n" +
                               "5. Result Operations\n" +
                               "6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    userOperations();
                    break;
                case 2:
                    examOperations();
                    break;
                case 3:
                    questionOperations();
                    break;
                case 4:
                    answerOperations();
                    break;
                case 5:
                    resultOperations();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
