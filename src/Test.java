import Courses.Coursedata;
import Users.Userdata;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Userdata userdata = Userdata.getInstance();
        Coursedata coursedata = Coursedata.getInstance();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String lineIn = scanner.nextLine();
            lineIn = lineIn.trim();
            if (lineIn.isEmpty()) {
                continue;
            }
            String[] op = lineIn.split("\\s+");
            switch (op[0]) {
                case "quit": {
                    System.out.print(userdata.quit(op));
                    System.exit(0);
                    break;
                }

                case "register": {
                    System.out.println(userdata.register(op));
                    break;
                }

                case "login": {
                    System.out.println(userdata.login(op));
                    break;
                }

                case "logout": {
                    System.out.println(userdata.logout(op));
                    break;
                }

                case "printInfo": {
                    System.out.println(userdata.printInfo(op));
                    break;
                }

                case "createCourse": {
                    System.out.println(coursedata.createCourse(op));
                    break;
                }

                case "listCourse": {
                    System.out.println(coursedata.listCourse(op));
                    break;
                }

                case "selectCourse": {
                    System.out.println(userdata.selectCourse(op));
                    break;
                }
                default: {
                    System.out.println("Command '" + op[0] + "' not found");
                    break;
                }
            }
        }

        scanner.close();
    }
}
