package burukeyou.im.server.entity.enums;

public enum MsgActionEnum {

    CONNECT(0),

    MSG(1),

    HEART(2);

    private Integer action;

    MsgActionEnum(Integer action) {
        this.action = action;
    }

    public Integer getAction() {
        return action;
    }
}
