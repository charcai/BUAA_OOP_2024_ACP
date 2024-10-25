package Users;

public class Student extends User {
	public Student(IdentityEnum identity) {
		super(identity);
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
