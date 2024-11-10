package Courses;

public class OutputCourseInfo implements java.io.Serializable {
    String name;
    courseTime time;
    double credit;
    int hours;
    public OutputCourseInfo(String name, courseTime time, double credit, int hours) {
        this.name = name;
        this.time = time;
        this.credit = credit;
        this.hours = hours;
    }
}
