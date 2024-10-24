package Testers;
import Users.Student;

import java.util.Scanner;

public class TestPasswordCheck {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            String ss;
            ss = input.nextLine();
            System.out.println(Student.passwordCheck(ss));
        }
    }
}
