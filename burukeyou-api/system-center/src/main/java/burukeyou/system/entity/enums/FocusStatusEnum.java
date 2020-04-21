package burukeyou.system.entity.enums;

public enum FocusStatusEnum {

     FOCUS("已关注"),

     ALL("全部"),

     NOFOCUS("未关注");


    private String status;

    FocusStatusEnum(String status) {
        this.status = status;
    }

    public String VALUE() {
        return status;
    }
}
