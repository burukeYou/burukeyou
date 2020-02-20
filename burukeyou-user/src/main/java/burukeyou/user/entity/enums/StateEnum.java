package burukeyou.user.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  StateEnum {

    PendingReview(0),  //未审核

    AlreadyReview(1),  // 审核通过

    Forbid(3); // 禁止

    private int state;


    public int getState() {
        return state;
    }
}
