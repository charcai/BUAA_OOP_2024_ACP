package Users;

public class Administrator extends User {
    public Administrator(IdentityEnum identity) {
        super(identity);
    }
    public static boolean idCheck(String id){
        int len = id.length();
        if(len != 5) {
            return false;
        }
        String prefix = id.substring(0, 2);
        if(!prefix.equals("AD")) {
            return false;
        }
        for(int i = 2; i < 5; ++i) {
            if(!isNumber(id.charAt(i))) {
                return false;
            }
        }
        return Integer.parseInt(id.substring(2, 5)) != 0;
    }
}
