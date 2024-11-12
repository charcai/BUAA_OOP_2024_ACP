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
        List<CourseInfo> teacherCourses = new ArrayList<>();
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

    public static String inputCourseBatch(String path) throws IOException, ClassNotFoundException{
        path = "./data/" + path;
        File file = new File(path);
        if(!file.exists()) {
            return "File does not exist";
        }
        if(file.isDirectory()) {
            return "File is a directory";
        }
        List<CourseInfo> importCourses;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            importCourses = (List<CourseInfo>) ois.readObject();
        }
        StringBuilder ss = new StringBuilder();
        boolean flag = false;
        for(CourseInfo info : importCourses) {
            String timeExp = info.time.weekday + "_" + info.time.beginTime + "-" + info.time.endTime;
            String ret = Coursedata.getInstance().createCourse(new String[]{
                    "",
                    info.name,
                    timeExp,
                    String.valueOf(info.credit),
                    String.valueOf(info.hours)
            }, true) + System.lineSeparator();
            /*if(ret.contains("Create course success")) {
                flag = true;
            }*/
            ss.append(ret);
        }

        //  Success
        return ss + /*(flag ? "Input course batch success" : "") */"Input course batch success" + System.lineSeparator();
    }
}
