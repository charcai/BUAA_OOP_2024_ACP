package Users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Courses.Coursedata;
import Courses.courseTime;

public class Teacher extends User {
    public List<Integer> courses = new ArrayList<>();
    public boolean[][] occupation = new boolean[8][15];
    public Teacher(IdentityEnum identity) {
        super(identity);
    }
    public boolean hasCourse() {
        Coursedata coursedata = Coursedata.getInstance();
        for (Integer course : courses) {
            if (!coursedata.getCourse(course).cancelled) {
                return true;
            }
        }
        return false;
    }
    public void removeOccupation(courseTime T) {
        for(int i = T.beginTime; i <= T.endTime; ++i) {
            occupation[T.weekday][i] = false;
        }
    }
    public static boolean idCheck(String id) {
        return id.matches("^(?!0{5})\\d{5}$");
    }
    static Comparator<Teacher> nameOrder = Comparator.comparing(o -> o.name);
}
