package Users;

public class Administrator extends User {
    public Administrator(IdentityEnum identity) {
        super(identity);
    }
    public static boolean idCheck(String id){
        return id.matches("^AD(?!000)\\d{3}$");
    }
}
