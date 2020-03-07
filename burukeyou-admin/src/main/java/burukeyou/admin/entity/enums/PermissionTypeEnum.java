package burukeyou.admin.entity.enums;

public enum PermissionTypeEnum {
    MENU("MENU"),

    BUTTON("BUTTON");

    private String type;

    PermissionTypeEnum(String type) {
        this.type = type;
    }

    public String Type() {
        return type;
    }
}
