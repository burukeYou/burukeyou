package burukeyou.focus.entity.enums;

public enum  FocusStatusEnum {

    LIKE(true),

    UNLIKE(false);

    private boolean status;


    FocusStatusEnum(boolean status) {
        this.status = status;
    }

    public boolean VALUE() {
        return status;
    }
}
