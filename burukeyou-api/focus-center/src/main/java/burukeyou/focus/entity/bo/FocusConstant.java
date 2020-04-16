package burukeyou.focus.entity.bo;

public class FocusConstant {

    /**
     *      保存 的 key
     *      用户Id::被点赞targetId::被点赞targetType  -- true
     */
    public final static String FOCUS_STATUS_KEY = "FOCUS_STATUS_KEY";

    /**
     *      保存 的 key
     *      targetId - like count
     */
    public final static String FOCUS_COUNT_KEY = "FOCUS_COUNT_KEY";


    /**
     *  构建 foucs data key
     * @return
     */
    public static String buildFocusStatusKey(String userId,String targetId,String targetType){
        StringBuilder builder = new StringBuilder();
        builder.append(userId).append("::").append(targetId).append("::").append(targetType);
        return builder.toString();
    }

    /**
     *  构建 foucus count key
     *
     */
    public  static  String bulidFocusCountKey(String targetId,String targetType){
        return new StringBuilder().append(targetId).append("::").append(targetType).toString();
    }




}
