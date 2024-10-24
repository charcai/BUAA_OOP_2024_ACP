package Testers;

import Users.Teacher;
import Users.Student;
import java.util.Scanner;

public class TestIDcheck {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String ss = input.nextLine();
            System.out.println(Teacher.idCheck(ss));
        }
    }
}
