package Users;


public abstract class User {
    public enum IdentityEnum {
        NOT_APPOINTED, STUDENT, TEACHER, ADMINISTRATOR
    }

    protected String id, password, name;
    IdentityEnum identity;
    public User() {this("", "", "", IdentityEnum.NOT_APPOINTED);}
    public User(IdentityEnum identity) {
        this("", "", "", identity);
    }
    public User(String id, String name, String password, IdentityEnum identity) {
        set(id, name, password);
        this.identity = identity;
    }
    public static boolean nameCheck(String nn){
        char c0 = nn.charAt(0);
        if(!isAlpha(c0)) {
            return false;
        }
        int len = nn.length();
        if(len < 4 || len > 16){
            return false;
        }
        for(int i = 1; i < len; i++){
            char ci = nn.charAt(i);
            if(ci != '_' && !isAlpha(ci)){
                return false;
            }
        }
        return true;
    }
    public static boolean passwordCheck(String pw){
        int len = pw.length();
        if(len < 6 || len > 16){
            return false;
        }
        boolean hasDigit = false;
        boolean hasNumber = false;
        boolean hasSign = false;
        for(int i = 0; i < len; i++){
            char ci = pw.charAt(i);
            hasDigit |= isAlpha(ci);
            hasNumber |= isNumber(ci);
            hasSign |= isSign(ci);
            if(!isAlpha(ci) && !isNumber(ci) && !isSign(ci)) {
                return false;
            }
        }
        return hasDigit && hasNumber && hasSign;
    }
    public static boolean identityCheck(String t){
        return switch(t) {
            case "Administrator", "Teacher", "Student" -> true;
            default -> false;
        };
    }

    public void set(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    protected static boolean isAlpha(char cc) {
        if(cc >= 'a' && cc <= 'z'){
            return true;
        }
        return cc >= 'A' && cc <= 'Z';
    }
    protected static boolean isNumber(char cc) {
        return cc >= '0' && cc <= '9';
    }
    protected static boolean isSign(char cc) {
        return switch(cc) {
            case '@', '_', '%', '$' -> true;
            default -> false;
        };
    }
}
