package burukeyou.comment.entity.dto;

import burukeyou.comment.entity.pojo.AmsReply;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import lombok.Data;


@Data
public class ReplyDto implements BaseInputConverter<AmsReply> {

    private String respUserId; //被回复用户

    private String respUserNickname;

    private String parentId;

    private String parentType;

    private String commentId;

    private String content;
}
