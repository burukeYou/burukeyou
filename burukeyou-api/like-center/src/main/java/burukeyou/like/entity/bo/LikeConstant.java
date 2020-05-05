package burukeyou.like.entity.bo;

public class LikeConstant {

    private LikeConstant(){}

    /**
     *      保存 的 key
     *      用户Id::被点赞targetId::被点赞targetType  -- true
     */
    public final static String LIKE_STATUS_KEY = "LIKE_STATUS_KEY";

    /**
     *      保存 的 key
     *      targetId - like count
     */
    public final static String LIKE_COUNT_KEY = "LIKE_COUNT_KEY";


    /**
     *  构建 like data key
     * @return
     */
    public static String buildLikeStatusKey(String userId,String targetId,String targetType){
        StringBuilder builder = new StringBuilder();
        builder.append(userId).append("::").append(targetId).append("::").append(targetType);
        return builder.toString();
    }

    /**
     *  构建 like count key
     *
     */
    public  static  String bulidLikeCountKey(String targetId,String targetType){
        return new StringBuilder().append(targetId).append("::").append(targetType).toString();
    }

}
