package burukeyou.notification.entity.enums;

public enum NotificationTypeEnum {

    SYSTEM("SYSTEM"),

    USER("USER");

    private String type;

    NotificationTypeEnum(String type) {
        this.type = type;
    }

    public String VALUE() {
        return type;
    }
}
