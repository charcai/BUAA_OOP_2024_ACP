package Testers;

import Users.Student;
import java.util.Scanner;

public class TestNameCheck {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String ss = input.nextLine();
            System.out.println(Student.nameCheck(ss));
        }
    }
}
