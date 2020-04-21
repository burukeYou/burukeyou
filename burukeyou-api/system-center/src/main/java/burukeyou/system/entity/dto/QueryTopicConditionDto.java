package burukeyou.system.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryTopicConditionDto {

    private int page;

    private int size;

    private String name;

    private String orderField;

    private String order;
}
