package burukeyou.system.entity.enums;

public enum FocusTargetEnums {

    USER("USER"),

    TOPIC("TOPIC"),

    LABEL("LABEL"),

    ARTICLE("ARTICLE"),

    BOILING("BOILING"),

    VIDEO("VIDEO");

    private String type;

    FocusTargetEnums(String type) {
        this.type = type;
    }

    public String VALUE() {
        return type;
    }
}
