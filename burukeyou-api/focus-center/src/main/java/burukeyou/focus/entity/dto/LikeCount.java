package burukeyou.focus.entity.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeCount {

    private String targetId;

    private String targetType;

    private Integer count;
}
