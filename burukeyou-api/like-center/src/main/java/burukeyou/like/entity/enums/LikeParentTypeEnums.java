package burukeyou.like.entity.enums;

public enum LikeParentTypeEnums {

    USER("USER"),

    BOILING("BOILING"),

    ARTICLE("ARTICLE"),

    VIDEO("VIDEO");

    private String type;

    LikeParentTypeEnums(String type) {
        this.type = type;
    }

    public String VALUE() {
        return type;
    }
}
