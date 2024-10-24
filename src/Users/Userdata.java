package Users;

import java.util.HashMap;
import java.util.Map;

public class Userdata {
    private final static Map<String, User> map = new HashMap<>();
    private Userdata() {}
    private static final Userdata userdata = new Userdata();
    public static Userdata getInstance() {
        return userdata;
    }
    public void register(String[] op) {

    }
}
