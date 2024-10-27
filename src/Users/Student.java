package Users;

import Courses.Course;
import Courses.Coursedata;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
	private final List<Integer> chosen = new ArrayList<>();
	private final boolean[][] occupied = new boolean[8][15];
	public Student(IdentityEnum identity) {
		super(identity);
	}

	public String selectCourse(int id) {
		Course course = Coursedata.getInstance().getCourse(id);
		for(int i = course.time.beginTime; i <= course.time.endTime; ++i) {
			if(occupied[course.time.weekday][i]) {
				return "Course time conflicts";
			}
		}
		for(int i = course.time.beginTime; i <= course.time.endTime; ++i) {
			occupied[course.time.weekday][i] = true;
		}
		chosen.add(id);
		return "Select course success (courseId: C-" + id + ")";
	}

	public static boolean idCheck(String id) {
		return id.length() == 8 ? idCheckUndergraduate(id) :
				id.length() == 9 && (idCheckMaster(id) || idCheckDoctor(id));
	}
	private static boolean idCheckUndergraduate(String id) {
		return id.matches("^(19|2[0-4])(0[1-9]|[1-3][0-9]|4[0-3])[1-6](?!00)\\d{3}$");
	}
	private static boolean idCheckMaster(String id) {
		return id.matches("^(SY|ZY)(21|2[2-4])(0[1-9]|[1-3][0-9]|4[0-3])[1-6](?!00)\\d{2}$");
	}
	private static boolean idCheckDoctor(String id) {
		return id.matches("^BY(19|2[0-4])(0[1-9]|[1-3][0-9]|4[0-3])[1-6](?!00)\\d{2}$");
	}
}
