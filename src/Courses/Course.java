package Courses;

public class Course extends courseTime{
    public String name;
    public double credit;
    public int hours;
    public courseTime time;
    public boolean cancelled = false;

    public Course(String name, double credit, courseTime coursetime, int hours) {
        this.name = name;
        this.credit = credit;
        this.time = coursetime;
        this.hours = hours;
    }

}
