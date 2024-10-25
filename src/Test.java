import Users.Userdata;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Userdata userdata = Userdata.getInstance();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String lineIn = scanner.nextLine();
            lineIn = lineIn.trim();
            String[] op = lineIn.split("\\s+");
            switch (op[0]) {
                case "quit":
                    if(op.length != 1) {
                        System.out.println("Illegal argument count");
                        break;
                    }
                    //TODO: 顺序打印登出用户信息
                    System.out.println("----- Good Bye! -----");
                    System.exit(0);
                    break;

                case "register":
                    int ret = userdata.register(op);
                    System.out.println(Const.registerCode[ret]);
                    break;

                case "login":
                    System.out.println(userdata.login(op));
                    break;

                case "logout":
                    System.out.println(userdata.logout(op));
                    break;
//TODO: main menu
                default:
                    System.out.println("Command '" + op[0] + "' not found");
                    break;
            }
        }
    }
}
