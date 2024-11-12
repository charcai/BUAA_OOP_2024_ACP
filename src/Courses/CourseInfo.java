package Courses;

public class CourseInfo implements java.io.Serializable {
    String name;
    courseTime time;
    double credit;
    int hours;
    public CourseInfo(String name, courseTime time, double credit, int hours) {
        this.name = name;
        this.time = time;
        this.credit = credit;
        this.hours = hours;
    }
    public String toString() {
        return time.weekday + "_" + time.beginTime + "-" + time.endTime;
    }
}
