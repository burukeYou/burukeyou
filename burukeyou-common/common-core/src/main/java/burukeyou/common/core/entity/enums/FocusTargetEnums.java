package burukeyou.common.core.entity.enums;

public enum FocusTargetEnums {

    USER("USER"),

    TOPIC("TOPIC"),

    LABEL("LABEL");

    private String type;

    FocusTargetEnums(String type) {
        this.type = type;
    }

    public String VALUE() {
        return type;
    }
}
