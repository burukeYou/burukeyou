package burukeyou.comment.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document(collection = "comment")
public class AmsComment  implements Serializable {

	@Id
	private String _id;  // mongdb 主键必须以 _ 开头

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String parentId;

	private String parentType;

	private String content;

	private int thumbupCount;

	private int replyCount;

	@JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss",timezone="GMT+8")
	private LocalDateTime createdTime;

}
