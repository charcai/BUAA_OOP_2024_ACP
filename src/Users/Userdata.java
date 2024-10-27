package Users;

import Courses.Course;
import Courses.Coursedata;

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

    private final Map<String, User> map = new HashMap<>();
    private final List<String> online = new ArrayList<>();
    private String currentUser = "";
    public final List<Teacher> teachers = new ArrayList<>();

    public String quit(String[] op) {
        if(op.length != 1) {
            return "Illegal argument count";
        }
        StringBuilder ret = new StringBuilder();
        for(String enumOnline : online) {
            ret.append(enumOnline).append(" Bye~").append(System.lineSeparator());
        }
        return ret + "----- Good Bye! -----";
    }
    public String register(String[] op) {
        if(op.length != 6) {
            return "Illegal argument count";
        }
        User user;

        //  check ID
        if(Student.idCheck(op[1])) {
            user = new Student(IdentityEnum.STUDENT);
        }
        else if(Teacher.idCheck(op[1])) {
            user = new Teacher(IdentityEnum.TEACHER);
            userdata.teachers.add((Teacher)user);
        }
        else if(Administrator.idCheck(op[1])) {
            user = new Administrator(IdentityEnum.ADMINISTRATOR);
        }
        else {
            return "Illegal user id";
        }

        //  check Unique
        if(map.containsKey(op[1])) {
            return "User id exists";
        }

        //  check name
        if(!User.nameCheck(op[2])) {
            return "Illegal user name";
        }

        //  check password
        if(!User.passwordCheck(op[3])) {
            return "Illegal password";
        }

        //  check password same
        if(!op[3].equals(op[4])) {
            return "Passwords do not match";
        }

        //  check identity
        if(!User.identityCheck(op[5])) {
            return "Illegal identity";
        }
        user.set(op[1], op[2], op[3]);
        map.put(op[1], user);
        return "Register success";
    }
    public String login(String[] op) {
        if(op.length != 3) {
            return "Illegal argument count";
        }
        if(User.idInvalid(op[1])) {
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
                if(noOnline()) {
                    return "No one is online";
                }
                online.remove(currentUser);
                String s = currentUser;
                currentUser = "";
                return s + " Bye~";

            case 2:
                if(noOnline()) {
                    return "No one is online";
                }
                if(getUser(currentUser).identity != IdentityEnum.ADMINISTRATOR) {
                    return "Permission denied";
                }
                if(User.idInvalid(op[1])) {
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
    public String printInfo(String[] op) {
        switch(op.length) {
            case 1:
                if(currentUser.isEmpty()) {
                    return "No one is online";
                }

                return infoToString(getUser(currentUser));

            case 2:
                if(currentUser.isEmpty()) {
                    return "No one is online";
                }
                User currentUserInstance = getUser(currentUser);

                if(currentUserInstance.identity != IdentityEnum.ADMINISTRATOR) {
                    return "Permission denied";
                }

                if(User.idInvalid(op[1])) {
                    return "Illegal user id";
                }

                User targetUser = map.get(op[1]);
                if(targetUser == null) {
                    return "User does not exist";
                }

                return infoToString(targetUser);

            default:
                return "Illegal argument count";
        }
    }
    public String selectCourse(String[] op) {
        Coursedata coursedata = Coursedata.getInstance();
        if(op.length != 2) {
            return "Illegal argument count";
        }
        if(noOnline()) {
            return "No one is online";
        }
        User user = getCurrentUser();
        if(user.identity != IdentityEnum.STUDENT) {
            return "Permission denied";
        }
        if(!Course.idCheck(op[1])) {
            return "Illegal course id";
        }

        int courseId = Course.splitId(op[1]);
        if(courseId > coursedata.courseId) {
            return "Course does not exist";
        }
        if(coursedata.getCourse(courseId).cancelled) {
            return "Course does not exist";
        }
        return ((Student)user).selectCourse(courseId);
    }

    private String infoToString(User currentUserInstance) {
        return  "User id: " + currentUserInstance.id + System.lineSeparator()
                + "Name: " + currentUserInstance.name + System.lineSeparator()
                + "Type: " + currentUserInstance.identity.toString() + System.lineSeparator()
                + "Print information success";
    }
    public User getUser(String ss) {
        return map.get(ss);
    }
    public User getCurrentUser(){
        return getUser(currentUser);
    }
    public boolean noOnline() {
        return currentUser.isEmpty();
    }
    public void sortTeachers() {
        teachers.sort(Teacher.nameOrder);
    }
}
