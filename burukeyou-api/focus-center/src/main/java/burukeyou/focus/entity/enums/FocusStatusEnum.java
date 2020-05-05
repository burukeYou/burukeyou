package burukeyou.focus.entity.enums;

public enum  FocusStatusEnum {

    FOCUS(true),

    UNFOCUS(false);

    private boolean status;


    FocusStatusEnum(boolean status) {
        this.status = status;
    }

    public boolean VALUE() {
        return status;
    }
}
