package burukeyou.boiling.entity.dto;

import lombok.Data;

@Data
public class QueryConditionDto {

    private String userId;

    private String loginUserId;

    private int page;

    private int size;

    private String topicId;
}
