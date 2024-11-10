package Courses;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String name;
    public double credit;
    public int hours;
    public courseTime time;
    String teacher;
    public List<String> students = new ArrayList<>();
    public boolean cancelled = false;

    public Course(String name, double credit, courseTime coursetime, int hours, String teacher) {
        this.name = name;
        this.credit = credit;
        this.time = coursetime;
        this.hours = hours;
        this.teacher = teacher;
    }

    public OutputCourseInfo outputInfo() {
        return new OutputCourseInfo(name, time, credit, hours);
    }

    public static boolean idCheck(String ss) {
        return ss.matches("^C-[1-9]\\d*$");
    }
    public static int splitId(String ss) {
        return Integer.parseInt(ss.substring(2));
    }

}
