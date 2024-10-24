package Users;

public class Teacher extends User {
    public static boolean idCheck(String id) {
        int len = id.length();
        if(len != 5) {
            return false;
        }
        for(int i = 0; i < 5; i++) {
            if(!isNumber(id.charAt(i))) {
                return false;
            }
        }
        return Integer.parseInt(id) != 0;
    }
}
