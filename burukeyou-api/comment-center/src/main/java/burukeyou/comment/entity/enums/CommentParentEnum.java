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
}
