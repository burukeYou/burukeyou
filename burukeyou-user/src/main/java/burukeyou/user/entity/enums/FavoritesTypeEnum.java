package burukeyou.user.entity.enums;

public enum  FavoritesTypeEnum {

    Article(0),

    Video(1);

    private Integer type;

    FavoritesTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
