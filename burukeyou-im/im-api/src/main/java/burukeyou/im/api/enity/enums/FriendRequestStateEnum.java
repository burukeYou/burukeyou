package burukeyou.im.api.enity.enums;

public enum FriendRequestStateEnum {

    PendingPass(0),  // 待审阅

    PASS(1),  //  通过好友请求

    NOTPASS(2), // 拒绝好友请求

    IGNORE(3);  //忽略

    private int state;

    FriendRequestStateEnum(int state) {
        this.state = state;
    }

    public int State() {
        return state;
    }
}
