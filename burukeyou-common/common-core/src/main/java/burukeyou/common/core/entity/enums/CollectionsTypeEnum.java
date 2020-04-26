package burukeyou.common.core.entity.enums;

public enum CollectionsTypeEnum {

    Article("Article"),

    Video("Video");

    private String type;

    CollectionsTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean isExist(String type){
        for (CollectionsTypeEnum e : CollectionsTypeEnum.values())
            if (e.getType().equals(type))
                return true;
        return false;
    }
}
