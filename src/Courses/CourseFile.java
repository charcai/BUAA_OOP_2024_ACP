package Courses;

import Users.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseFile implements Serializable {
    public static void outputCourseBatch(String path, Teacher teacher) throws IOException{
        path = "./data/" + path;
        File file = new File(path);
        file.getParentFile().mkdirs();
        Coursedata coursedata = Coursedata.getInstance();
        List<OutputCourseInfo> teacherCourses = new ArrayList<>();
        for(int i : teacher.courses) {
            Course currCourse = coursedata.getCourse(i);
            if(!currCourse.cancelled) {
                teacherCourses.add(currCourse.outputInfo());
            }
        }
        try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(file))) {
            OOS.writeObject(teacherCourses);
        }
    }
}
