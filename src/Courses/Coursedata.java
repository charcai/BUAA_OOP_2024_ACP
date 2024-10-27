package Courses;

import Users.IdentityEnum;
import Users.Teacher;
import Users.User;
import Users.Userdata;

import java.util.ArrayList;
import java.util.List;

public class Coursedata {
    private Coursedata() {}
    private static final Coursedata coursedata = new Coursedata();
    public static Coursedata getInstance() {
        return coursedata;
    }

    private final static List<Course> list = new ArrayList<>();
    public int courseId = 0;

    public Course getCourse(int id) {
        return list.get(id - 1);
    }
    public String createCourse(String[] op) {
        if(op.length != 5) {
            return "Illegal argument count";
        }
        Userdata userdata = Userdata.getInstance();
        User user = userdata.getCurrentUser();

        if(user == null) {
            return "No one is online";
        }

        if(user.identity != IdentityEnum.TEACHER) {
            return "Permission denied";
        }

        Teacher teacher = (Teacher) user;
        if(teacher.courses.size() >= 10) {
            return "Course count reaches limit";
        }

        if(!op[1].matches("^[A-Za-z][A-Za-z0-9-_]{0,19}$")) {
            return "Illegal course name";
        }

        for(int i : teacher.courses) {
            Course C_i = getCourse(i);
            if(!C_i.cancelled) {
                if(C_i.name.equals(op[1])) {
                    return "Course name exists";
                }
            }
        }

        if(!op[2].matches("^[1-7]_([1-9]|1[0-4])-([1-9]|1[0-4])$")) {
            return "Illegal course time";
        }
        courseTime time = courseTime.extractTime(op[2]);
        if(time.beginTime > time.endTime) {
            return "Illegal course time";
        }

        for(int i = time.beginTime; i <= time.endTime; ++i) {
            if(teacher.occupation[time.weekday][i]) {
                return "Course time conflicts";
            }
        }

        double credit = Double.parseDouble(op[3]);
        if(credit <= 0 || credit > 12) {
            return "Illegal course credit";
        }

        int hours = Integer.parseInt(op[4]);
        if(hours <= 0 || hours > 1280) {
            return "Illegal course period";
        }

        for(int i = time.beginTime; i <= time.endTime; ++i) {
            teacher.occupation[time.weekday][i] = true;
        }
        list.add(new Course(op[1], credit, time, hours));
        teacher.courses.add(++courseId);
        return "Create course success (courseId: C-" + courseId + ")";
    }
    public String listCourse(String[] op) {
        Userdata userdata = Userdata.getInstance();
        switch(op.length) {
            case 1: {
                if(userdata.noOnline()) {
                    return "No one is online";
                }
                User user = userdata.getCurrentUser();
                if(user.identity == IdentityEnum.TEACHER) {
                    if(((Teacher) user).hasCourse()) {
                        return printCourse((Teacher) user, "") + "List course success";
                    }
                    else {
                        return "Course does not exist";
                    }
                }
                else {
                    userdata.sortTeachers();
                    boolean suc = false;
                    StringBuilder ss = new StringBuilder();
                    for(Teacher teacher : userdata.teachers) {
                        if(teacher.hasCourse()) {
                            suc = true;
                            ss.append(printCourse(teacher, teacher.getName()));
                        }
                    }
                    if(suc) {
                        return ss + "List course success";
                    }
                    else {
                        return "Course does not exist";
                    }
                }
            }

            case 2: {
                if(userdata.noOnline()) {
                    return "No one is online";
                }
                User user = userdata.getCurrentUser();
                if(user.identity != IdentityEnum.ADMINISTRATOR) {
                    return "Permission denied";
                }
                if(User.idInvalid(op[1])) {
                    return "Illegal user id";
                }
                User targetUser = userdata.getUser(op[1]);
                if(targetUser == null) {
                    return "User does not exist";
                }
                if(targetUser.identity != IdentityEnum.TEACHER) {
                    return "User id does not belong to a Teacher";
                }
                Teacher teacher = (Teacher) targetUser;
                if(!teacher.hasCourse()) {
                    return "Course does not exist";
                }
                return printCourse(teacher, teacher.getName()) + "List course success";
            }

            default: {
                return "Illegal argument count";
            }
        }
    }
    public String printCourse(Teacher teacher, String name) {
        if(!name.isEmpty()) {
            name = name + " ";
        }
        StringBuilder ss = new StringBuilder();
        for(int i : teacher.courses) {
            Course currCourse = coursedata.getCourse(i);
            if(!currCourse.cancelled) {
                ss.append(name).append("C-").append(i).append(" ").append(currCourse.name).append(" ");
                ss.append(currCourse.time.weekday).append('_').append(currCourse.time.beginTime).append('-').append(currCourse.time.endTime).append(" ");
                ss.append(String.format("%.1f", currCourse.credit)).append(" ").append(currCourse.hours).append(System.lineSeparator());
            }
        }
        return ss.toString();
    }
}
