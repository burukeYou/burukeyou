package burukeyou.comment.entity.enums;


public enum CommentParentEnum {

    ARTICLE("Article"),
    
    COMMENT("Comment"),

    VIDERO("Video"),

    BOILING("Boiling");

    private String type;

    CommentParentEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String getTypeName(String type){
        if (ARTICLE.type.equals(type))
            return "文章";
        else if (COMMENT.type.equals(type))
            return "评论";
        else if (BOILING.type.equals(type))
            return "沸点";
        return null;
    }
}
