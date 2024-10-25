package Users;

public enum IdentityEnum {
    NOT_APPOINTED("Not_Appointed"),
    STUDENT("Student"),
    TEACHER("Teacher"),
    ADMINISTRATOR("Administrator");

    String Type;
    IdentityEnum(String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return Type;
    }
}