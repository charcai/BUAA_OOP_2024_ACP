package Users;

public class Student extends User {
	public Student(IdentityEnum identity) {
		super(identity);
	}
	public static boolean idCheck(String id) {
		boolean flag = false;
		int len = id.length();
		flag = switch (len) {
			case 8 -> flag | idCheckUndergraduate(id);
			case 9 -> {
				flag = flag | idCheckMaster(id);
				yield flag | idCheckDoctor(id);
			}
			default -> false;
		};
		return flag;
	}
	private static boolean idCheckUndergraduate(String id) {
		for(int i = 0; i < 8; i++) {
			char c = id.charAt(i);
			if(c < '0' || c > '9') {
				return false;
			}
		}
		int Year = Integer.parseInt(id.substring(0,2));
		int School = Integer.parseInt(id.substring(2,4));
		int Class = Integer.parseInt(id.substring(4,5));
		int iden = Integer.parseInt(id.substring(5,8));
		if(Year < 19 || Year > 24) {
			return false;
		}
		if(School < 1 || School > 43) {
			return false;
		}
		if(Class < 1 || Class > 6) {
			return false;
		}
		return iden != 0;
	}
	private static boolean idCheckMaster(String id) {
		for(int i = 2; i < 9; ++i) {
			char c = id.charAt(i);
			if(c < '0' || c > '9') {
				return false;
			}
		}
		String type = id.substring(0,2);
		int Year = Integer.parseInt(id.substring(2,4));
		int School = Integer.parseInt(id.substring(4,6));
		int Class = Integer.parseInt(id.substring(6,7));
		int iden = Integer.parseInt(id.substring(7,9));
		if(!type.equals("SY") && !type.equals("ZY")) {
			return false;
		}
		if(Year < 21 || Year > 24) {
			return false;
		}
		if(School < 1 || School > 43) {
			return false;
		}
		if(Class < 1 || Class > 6) {
			return false;
		}
		return iden != 0;
	}
	private static boolean idCheckDoctor(String id) {
		for(int i = 2; i < 9; ++i) {
			char c = id.charAt(i);
			if(c < '0' || c > '9') {
				return false;
			}
		}
		String type = id.substring(0,2);
		int Year = Integer.parseInt(id.substring(2,4));
		int School = Integer.parseInt(id.substring(4,6));
		int Class = Integer.parseInt(id.substring(6,7));
		int iden = Integer.parseInt(id.substring(7,9));
		if(!type.equals("BY")) {
			return false;
		}
		if(Year < 19 || Year > 24) {
			return false;
		}
		if(School < 1 || School > 43) {
			return false;
		}
		if(Class < 1 || Class > 6) {
			return false;
		}
		return iden != 0;
	}
}
