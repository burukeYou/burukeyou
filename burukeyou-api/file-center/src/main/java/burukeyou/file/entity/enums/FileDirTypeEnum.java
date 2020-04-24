package burukeyou.file.entity.enums;

public enum FileDirTypeEnum {

    Article("article/"),

    Boiling("boiling/"),

    Label("label/"),

    Topic("topic/"),

    UserAvatar("userAvatar/"),

    Video("video/");

    private String type;

    FileDirTypeEnum(String type) {
        this.type = type;
    }

    public String VALUE() {
        return type;
    }

    public static boolean isExits(String type){
        for (FileDirTypeEnum e : FileDirTypeEnum.values())
            if (e.VALUE().equals(type))
                return true;
        return false;
    }

}
