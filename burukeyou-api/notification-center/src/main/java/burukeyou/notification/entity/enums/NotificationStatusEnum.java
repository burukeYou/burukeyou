package burukeyou.notification.entity.enums;

public enum NotificationStatusEnum {

    UNREAD(false),

    READ(true);

    private boolean status;

    NotificationStatusEnum(boolean status) {
        this.status = status;
    }

    public boolean VALUE() {
        return status;
    }
}
