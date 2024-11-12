package Users;

import Courses.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student extends User {
	public final List<Integer> chosen = new ArrayList<>();
	private final boolean[][] occupation = new boolean[8][15];
	public Student(IdentityEnum identity) {
		super(identity);
	}

	public String selectCourse(int id) {
		Course course = Coursedata.getInstance().getCourse(id);
		if(course.students.size() >= 30) {
			return "Course capacity is full";
		}
		for(int i = course.time.beginTime; i <= course.time.endTime; ++i) {
			if(occupation[course.time.weekday][i]) {
				return "Course time conflicts";
			}
		}
		for(int i = course.time.beginTime; i <= course.time.endTime; ++i) {
			occupation[course.time.weekday][i] = true;
		}
		chosen.add(id);
		course.students.add(this.id);
		return "Select course success (courseId: C-" + id + ")";
	}
	public void removeOccupation(courseTime T) {
		for(int i = T.beginTime; i <= T.endTime; ++i) {
			occupation[T.weekday][i] = false;
		}
	}
	public static boolean idCheck(String id) {
		return id.length() == 8 ? idCheckUndergraduate(id) :
				id.length() == 9 && (idCheckMaster(id) || idCheckDoctor(id));
	}
	private static boolean idCheckUndergraduate(String id) {
		return id.matches("^(19|2[0-4])(0[1-9]|[1-3][0-9]|4[0-3])[1-6](?!000)\\d{3}$");
	}
	private static boolean idCheckMaster(String id) {
		return id.matches("^(SY|ZY)(21|2[2-4])(0[1-9]|[1-3][0-9]|4[0-3])[1-6](?!00)\\d{2}$");
	}
	private static boolean idCheckDoctor(String id) {
		return id.matches("^BY(19|2[0-4])(0[1-9]|[1-3][0-9]|4[0-3])[1-6](?!00)\\d{2}$");
	}

	public static Comparator<String> gradeOrder = (o1, o2) -> {

        // Determine the type of student based on the ID prefix
		int type1 = getType(o1);
		int type2 = getType(o2);

		// First compare by type
		if (type1 != type2) {
			return Integer.compare(type1, type2);
		}

		// If types are the same, compare by ID lexicographically
		return o1.compareTo(o2);
	};

	private static int getType(String id) {
		if (id.startsWith("ZY")) {
			return 2; // Professional Master
		} else if (id.startsWith("SY")) {
			return 3; // Academic Master
		} else if (id.startsWith("BY")) {
			return 4; // Doctor
		} else {
			return 1; // Undergraduate
		}
	}
}
