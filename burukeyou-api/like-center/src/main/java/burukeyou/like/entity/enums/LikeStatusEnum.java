package burukeyou.like.entity.enums;

public enum LikeStatusEnum {

    LIKE(true),

    UNLIKE(false);

    private boolean status;

    LikeStatusEnum(boolean status) {
        this.status = status;
    }

    public boolean VALUE() {
        return status;
    }
}
