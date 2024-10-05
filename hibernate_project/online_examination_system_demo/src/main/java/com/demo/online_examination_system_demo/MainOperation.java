package com.demo.online_examination_system_demo;


/*import static AllOpration.AllOprationUser.userOperations;
import static AllOpration.AllOperationExam.examOperations;
import static AllOpration.AllOprationQuestion.questionOperations;
import static AllOpration.AllOprationAnswer.answerOperations;
import static AllOpration.AllOprationResult.resultOperations;*/
import com.demo.examinationsystem.entities.*;
import static com.exam.NewAllOperation.userOperations;
import static com.exam.NewAllOperation.examOperations;
import static com.exam.NewAllOperation.questionOperations;
import static com.exam.NewAllOperation.answerOperations;
import static com.exam.NewAllOperation.resultOperations;
import java.util.Scanner;

import com.demo.exception.ResourceNotFoundException;

public class MainOperation {

    static Scanner sc = new Scanner(System.in);

    public static void mainOps() throws ResourceNotFoundException {
        while (true) {
            System.out.println("Press 1: User Details\n"
                    + "Press 2: Exam Operations\n"
                    + "Press 3: Question Operations\n"
                    + "Press 4: Answer Operations\n"
                    + "Press 5: Result Operations\n"
                    + "Press 6: Quit");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    userOperations();
                    System.out.println("=======================================");
                    break;
                case 2:
                    examOperations();
                    System.out.println("=======================================");
                    break;
                case 3:
                    questionOperations();
                    System.out.println("=======================================");
                    break;
                case 4:
                    answerOperations();
                    System.out.println("=======================================");
                    break;
                case 5:
                    resultOperations();
                    System.out.println("=======================================");
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input, please try again.");
            }
        }
    }

    public static void main(String[] args) throws ResourceNotFoundException {
        mainOps();
    }
}
