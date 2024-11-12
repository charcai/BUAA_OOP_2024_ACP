package Courses;

import Users.*;

import java.util.ArrayList;
import java.util.Comparator;
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
    public String createCourse(String[] op, boolean Already) {
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
                    return Already ? "Course name already exists" : "Course name exists";
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
        double credit;
        try {
            credit = Double.parseDouble(op[3]);
        } catch(NumberFormatException e) {
            return "Illegal course credit";
        }
        if(credit <= 0 || credit > 12) {
            return "Illegal course credit";
        }

        int hours;
        try {
            hours = Integer.parseInt(op[4]);
        } catch(NumberFormatException e) {
            return "Illegal course period";
        }
        if(hours <= 0 || hours > 1280) {
            return "Illegal course period";
        }

        for(int i = time.beginTime; i <= time.endTime; ++i) {
            teacher.occupation[time.weekday][i] = true;
        }
        list.add(new Course(op[1], credit, time, hours, teacher.id));
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
    public String cancelCourse(String[] op) {
        if(op.length != 2) {
            return "Illegal argument count";
        }
        if(Userdata.getInstance().noOnline()) {
            return "No one is online";
        }
        if(!Course.idCheck(op[1])) {
            return "Illegal course id";
        }
        int currId = Course.splitId(op[1]);

        //  not registered
        if(currId > courseId) {
            return "Course does not exist";
        }

        Course currCourse = getCourse(currId);
        //  cancelled
        if(currCourse.cancelled) {
            return "Course does not exist";
        }
        User curr = Userdata.getInstance().getCurrentUser();
        switch(curr.identity) {
            case STUDENT: {
                Student currStudent = (Student) curr;
                if (!currStudent.chosen.contains(currId)) {
                    return "Course does not exist";
                }
                currStudent.removeOccupation(currCourse.time);
                currCourse.students.remove(currStudent.id);
                currStudent.chosen.remove((Integer) currId);
                break;
            }
            case TEACHER: {
                Teacher currTeacher = (Teacher) curr;
                if (!currTeacher.courses.contains(currId)) {
                    return "Course does not exist";
                }
                currCourse.cancelled = true;
                currTeacher.removeOccupation(currCourse.time);
                break;
            }
            case ADMINISTRATOR: {
                currCourse.cancelled = true;
                for(String currStu : currCourse.students) {
                    Student currStudent = (Student) Userdata.getInstance().getUser(currStu);
                    currStudent.removeOccupation(currCourse.time);
                }
                Teacher currTeacher = (Teacher) Userdata.getInstance().getUser(currCourse.teacher);
                currTeacher.removeOccupation(currCourse.time);
                break;
            }
        }
        return "Cancel course success (courseId: C-" + currId + ")";

    }

    public String outputCourseBatch(String[] op) {
        if(op.length != 2) {
            return "Illegal argument count";
        }
        if(Userdata.getInstance().noOnline()) {
            return "No one is online";
        }
        User curruser = Userdata.getInstance().getCurrentUser();
        if(!(curruser instanceof Teacher)) {
            return "Permission denied";
        }

        try {
            CourseFile.outputCourseBatch(op[1], (Teacher) curruser);
        } catch(Exception e) {
            return e.toString();
        }
        return "Output course batch success";
    }
    public String inputCourseBatch(String[] op) {
        if(op.length != 2) {
            return "Illegal argument count";
        }
        Userdata userdata = Userdata.getInstance();
        if(userdata.noOnline()) {
            return "No one is online";
        }
        User curruser = userdata.getCurrentUser();
        if(!(curruser instanceof Teacher)) {
            return "Permission denied";
        }
        String s = "Initialized inputCourseBatch";
        try {
            s = CourseFile.inputCourseBatch(op[1]);
        } catch(Exception e) {
            System.out.println(e);
        }
        return s;
    }

    public String listStudent(String[] op) {
        if(op.length != 2) {
            return "Illegal argument count";
        }

        if(!op[1].matches("C-\\d+")) {
            return "Illegal course id";
        }

        int currId = Course.splitId(op[1]);
        if(currId > list.size() + 1) {
            return "Course does not exist";
        }
        if(getCourse(currId).cancelled) {
            return "Course does not exist";
        }
        if(Userdata.getInstance().getCurrentUser().identity == IdentityEnum.TEACHER) {
            if(!((Teacher) Userdata.getInstance().getCurrentUser()).courses.contains(currId)) {
                return "Course does not exist";
            }
        }

        if(getCourse(currId).students.isEmpty()) {
            return "Student does not select course";
        }

        StringBuilder ss = new StringBuilder();
        getCourse(currId).students.sort(Student.gradeOrder);

        for(String currStu : getCourse(currId).students) {
            Student currStudent = (Student) Userdata.getInstance().getUser(currStu);
            ss.append(currStudent.id).append(": ").append(currStudent.getName()).append(System.lineSeparator());
        }
        ss.append("List student success");
        return ss.toString();
    }

    public String removeStudent(String[] op) {
        if(op.length < 2 || op.length > 3) {
            return "Illegal argument count";
        }

        if(Userdata.getInstance().noOnline()) {
            return "No one is online";
        }

        User curr = Userdata.getInstance().getCurrentUser();
        if(curr.identity == IdentityEnum.STUDENT) {
            return "Permission denied";
        }

        if(User.idInvalid(op[1])) {
            return "Illegal user id";
        }

        if(Userdata.getInstance().getUser(op[1]) == null) {
            return "User does not exist";
        }

        if(Userdata.getInstance().getUser(op[1]).identity != IdentityEnum.STUDENT) {
            return "User id does not belong to a Student";
        }

        Student currStudent = (Student) Userdata.getInstance().getUser(op[1]);

        Coursedata coursedata = Coursedata.getInstance();

        switch(op.length) {
            case 3: {
                int id;
                if(!op[2].matches("C-\\d+")) {
                    return "Illegal course id";
                }
                try {
                    id = Course.splitId(op[2]);
                } catch (NumberFormatException e) {
                    return "Illegal course id";
                }
                if(id - 1 > list.size()) {
                    return "Course does not exist";
                }
                if(getCourse(id).cancelled) {
                    return "Course does not exist";
                }
                if(curr.identity == IdentityEnum.TEACHER && !((Teacher) curr).courses.contains(id)) {
                    return "Course does not exist";
                }
                if(!getCourse(id).students.contains(currStudent.id)) {
                    return "Student does not select course";
                }

                currStudent.removeOccupation(getCourse(id).time);
                getCourse(id).students.remove(currStudent.id);
                currStudent.chosen.remove((Integer) id);
                return "Remove student success";
            }
            case 2: {
                switch(curr.identity) {
                    case TEACHER: {
                        Teacher currT = (Teacher) curr;
                        boolean suc = false;
                        for(int i : currT.courses) {
                            Course c = coursedata.getCourse(i);
                            if(c.cancelled) {
                                continue;
                            }
                            if(c.students.contains(currStudent.id)) {
                                currStudent.removeOccupation(c.time);
                                coursedata.getCourse(i).students.remove(currStudent.id);
                                currStudent.chosen.remove((Integer) i);
                                suc = true;
                            }
                        }
                        if(!suc) {
                            return "Student does not select course";
                        }
                        return "Remove student success";
                    }

                    case ADMINISTRATOR: {
                        boolean suc = false;
                        for(Course c : list) {
                            if(c.cancelled) {
                                continue;
                            }
                            if(c.students.contains(currStudent.id)) {
                                currStudent.removeOccupation(c.time);
                                c.students.remove(currStudent.id);
                                currStudent.chosen.remove(list.indexOf(c) + 1);
                                suc = true;
                            }
                        }
                        if(!suc) {
                            return "Student does not select course";
                        }
                        return "Remove student success";
                    }
                }
            }
            default: {
                return "No operations done during removeStudent";
            }
        }
    }

    public Comparator<Integer> scheduleOrder = (o1, o2) -> {
        // First compare by weekday
        Course c1 = getCourse(o1),
                c2 = getCourse(o2);
        int weekdayComparison = Integer.compare(c1.time.weekday, c2.time.weekday);
        if (weekdayComparison != 0) {
            return weekdayComparison;
        }
        // If weekdays are the same, compare by start time
        return Integer.compare(c1.time.beginTime, c2.time.beginTime);
    };

}
