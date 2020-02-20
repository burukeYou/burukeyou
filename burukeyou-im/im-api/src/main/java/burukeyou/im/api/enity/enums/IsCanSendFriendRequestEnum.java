package burukeyou.im.api.enity.enums;


public enum IsCanSendFriendRequestEnum {
	
	SUCCESS(0, "OK"),
	USER_NOT_EXIST(1, "此用户不存在"),
	NOT_YOURSELF(2, "不能添加自己为好友"),
	ALREADY_FRIENDS(3, "该用户已经是你的好友");
	
	public final Integer STATE;
	public final String msg;
	
	IsCanSendFriendRequestEnum(Integer state, String msg){
		this.STATE = state;
		this.msg = msg;
	}
	
	public Integer STAE() {
		return STATE;
	}  
	
	public static String Msg(Integer status) {
		for (IsCanSendFriendRequestEnum e : IsCanSendFriendRequestEnum.values()) {
			if (e.STATE == status) {
				return e.msg;
			}
		}
		return null;
	}
	
}
