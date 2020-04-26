package burukeyou.common.core.entity.enums;

public enum FavoritesTypeEnum {

    Article("Article"),

    Video("Video");

    private String type;

    FavoritesTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
