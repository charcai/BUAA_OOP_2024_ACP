package Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Userdata {
    //  Singleton
    private Userdata() {}
    private static final Userdata userdata = new Userdata();
    public static Userdata getInstance() {
        return userdata;
    }

    private final static Map<String, User> map = new HashMap<>();
    private final static List<String> online = new ArrayList<>();
    private static String currentUser = "";

    public int register(String[] op) {
        if(op.length != 6) {
            return 1;
        }
        User user;

        //  check ID
        if(Student.idCheck(op[1])) {
            user = new Student(User.IdentityEnum.STUDENT);
        }
        else if(Teacher.idCheck(op[1])) {
            user = new Teacher(User.IdentityEnum.TEACHER);
        }
        else if(Administrator.idCheck(op[1])) {
            user = new Administrator(User.IdentityEnum.ADMINISTRATOR);
        }
        else {
            return 2;
        }

        //  check Unique
        if(map.containsKey(op[1])) {
            return 3;
        }

        //  check name
        if(!User.nameCheck(op[2])) {
            return 4;
        }

        //  check password
        if(!User.passwordCheck(op[3])) {
            return 5;
        }

        //  check password same
        if(!op[3].equals(op[4])) {
            return 6;
        }

        //  check identity
        if(!User.identityCheck(op[5])) {
            return 7;
        }
        user.set(op[1], op[2], op[3]);
        map.put(op[1], user);
        return 0;
    }
    public String login(String[] op) {
        if(op.length != 3) {
            return "Illegal argument count";
        }
        if(!User.idCheck(op[1])) {
            return "Illegal user id";
        }
        if(!map.containsKey(op[1])) {
            return "User does not exist";
        }
        if(online.contains(op[1])) {
            return op[1] + " is online";
        }
        if(!map.get(op[1]).passwordCorrect(op[2])) {
            return "Wrong password";
        }
        online.add(op[1]);
        currentUser = op[1];
        return "Welcome to ACP, " + currentUser;
    }
    public String logout(String[] op) {
        switch(op.length) {
            case 1:
                if(currentUser.isEmpty()) {
                    return "No one is online";
                }
                online.remove(currentUser);
                String s = currentUser;
                currentUser = "";
                return s + " Bye~";

            case 2:
                if(currentUser.isEmpty()) {
                    return "No one is online";
                }
                if(map.get(currentUser).identity != User.IdentityEnum.ADMINISTRATOR) {
                    return "Permission denied";
                }
                if(!User.idCheck(op[1])) {
                    return "Illegal user id";
                }
                if(!map.containsKey(op[1])) {
                    return "User does not exist";
                }
                if(!online.contains(op[1])) {
                    return op[1] + " is not online";
                }
                online.remove(op[1]);
                if(currentUser.equals(op[1])) {
                    currentUser = "";
                }
                return op[1] + " Bye~";
            default:
                return "Illegal argument count";
        }


    }
}
