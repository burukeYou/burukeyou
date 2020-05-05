package burukeyou.like.entity.bo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeCount {

    private String parentId;

    private String parentType;

    private Integer count;
}
