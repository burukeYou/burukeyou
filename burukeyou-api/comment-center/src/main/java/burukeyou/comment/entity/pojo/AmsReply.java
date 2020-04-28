package burukeyou.comment.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "reply")
public class AmsReply {

    @Id
    private String _id;  // mongdb 主键必须以 _ 开头

    private String userId; // 评论用户

    private String userNickname;

    private String userAvatar;

    private String respUserId; //被回复用户

    private String respUserNickname;

    private String parentId;

    private String parentType;

    private String commentId;

    private String content;

    private int thumbupCount;

    //private int commentCount;

    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createdTime;
}
