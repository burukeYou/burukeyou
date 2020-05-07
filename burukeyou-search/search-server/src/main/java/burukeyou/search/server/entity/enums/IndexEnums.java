package burukeyou.search.server.entity.enums;

public enum  IndexEnums {

    USER("user"),

    LABEL("label"),

    ARTICLE("article");

    private String index;

    IndexEnums(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public static boolean  exits(String index){
        for (IndexEnums e : IndexEnums.values())
            if (e.getIndex().equals(index))
                return true;
        return false;
    }
}
