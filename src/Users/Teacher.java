package Users;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    public List<Integer> courses = new ArrayList<>();
    public boolean[][] occupation = new boolean[8][15];
    public Teacher(IdentityEnum identity) {
        super(identity);
    }
    public static boolean idCheck(String id) {
        return id.matches("^(?!0{5})\\d{5}$");
    }
}
