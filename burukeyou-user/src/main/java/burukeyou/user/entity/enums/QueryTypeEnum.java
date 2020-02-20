package burukeyou.user.entity.enums;

public enum QueryTypeEnum {

    FrontDesk("frontDesk"),

    Admin("admin");

    private String type;

    QueryTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
