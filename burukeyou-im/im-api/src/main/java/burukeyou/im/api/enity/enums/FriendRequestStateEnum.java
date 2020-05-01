package burukeyou.im.api.enity.enums;


public enum FriendRequestStateEnum {

    PendingPass(0),  // 待处理

    PASS(1),  //  通过好友请求

    NOTPASS(2), // 拒绝好友请求

    IGNORE(3);  //忽略

    private Integer state;

    FriendRequestStateEnum(int state) {
        this.state = state;
    }

    public int State() {
        return state;
    }

    public static boolean isExist(Integer index){
        for (FriendRequestStateEnum e : FriendRequestStateEnum.values()) {
            if (e.state.equals(index) && e.state != 0){
                return true;
            }
        }
        return false;
    }
}
