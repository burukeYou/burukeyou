package burukeyou.article.entity.enums;

public enum StateEnum {

     PendingReview(0),  //未审核

     PASS(1),  // 审核通过

     NOTPASS(2),  // 审核未通过

     Forbid(3); // 禁止

    private int state;


    StateEnum(int state) {
        this.state = state;
    }

    public int STATE() {
        return state;
    }
}
