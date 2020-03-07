package burukeyou.admin.entity.enums;

public enum  RoleTypeEnum {
    ROLE("role"),

    GROUP("group");

    private String type;

    RoleTypeEnum(String type) {
        this.type = type;
    }

    public String Type() {
        return type;
    }
}
