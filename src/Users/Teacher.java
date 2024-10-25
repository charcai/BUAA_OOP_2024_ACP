package Users;

public class Teacher extends User {
    public Teacher(IdentityEnum identity) {
        super(identity);
    }
    public static boolean idCheck(String id) {
        return id.matches("^(?!0{5})\\d{5}$");
    }
}
