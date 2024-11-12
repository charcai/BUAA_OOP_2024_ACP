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
                    if(op.length != 1) {
                        System.out.println("Illegal argument count");
                        break;
                    }
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
                    try {
                        System.out.println(userdata.logout(op));
                    } catch (Exception e) {
                        System.out.println("Error: logout" + e.getMessage());
                    }
                    break;
                }

                case "printInfo": {
                    try {
                        System.out.println(userdata.printInfo(op));
                    } catch (Exception e) {
                        System.out.println("Error: printInfo" + e.getMessage());
                    }
                    break;
                }

                case "createCourse": {
                    try {
                        System.out.println(coursedata.createCourse(op, true));
                    } catch (Exception e) {
                        System.out.println("Error: createCourse" + e.getMessage());
                    }
                    break;
                }

                case "listCourse": {
                    try {
                        System.out.println(coursedata.listCourse(op));
                    } catch (Exception e) {
                        System.out.println("Error: listCourse" + e.getMessage());
                    }
                    break;
                }

                case "selectCourse": {
                    try {
                        System.out.println(userdata.selectCourse(op));
                    } catch (Exception e) {
                        System.out.println("Error: selectCourse" + e.getMessage());
                    }
                    break;
                }

                case "cancelCourse": {
                    try {
                        System.out.println(coursedata.cancelCourse(op));
                    } catch (Exception e) {
                        System.out.println("Error: cancelCourse" + e.getMessage());
                    }
                    break;
                }

                case "switch": {
                    try {
                        System.out.println(userdata.switchUser(op));
                    } catch (Exception e) {
                        System.out.println("Error: switch" + e.getMessage());
                    }
                    break;
                }

                case "outputCourseBatch": {
                    try {
                        System.out.println(coursedata.outputCourseBatch(op));
                    } catch (Exception e) {
                        System.out.println("Error: outputCourseBatch" + e.getMessage());
                    }
                    break;
                }

                case "inputCourseBatch": {
                    try {
                        System.out.println(coursedata.inputCourseBatch(op));
                    } catch (Exception e) {
                        System.out.println("Error: inputCourseBatch" + e.getMessage());
                    }
                    break;
                }

                case "listStudent": {
                    try {
                        System.out.println(coursedata.listStudent(op));
                    } catch (Exception e) {
                        System.out.println("Error: listStudent" + e.getMessage());
                    }
                    break;
                }

                case "removeStudent": {
                    try {
                        System.out.println(coursedata.removeStudent(op));
                    } catch (Exception e) {
                        System.out.println("Error: removeStudent" + e.getMessage());
                    }
                    break;
                }

                case "listCourseSchedule": {
                    try {
                        System.out.println(userdata.listCourseSchedule(op));
                    } catch (Exception e) {
                        System.out.println("Error: listCourseSchedule" + e.getMessage());
                    }
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
