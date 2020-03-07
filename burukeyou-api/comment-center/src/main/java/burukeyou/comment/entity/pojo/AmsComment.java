package burukeyou.comment.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "comment")
public class AmsComment  implements Serializable {

	@Id
	private String _id;  // mongdb 主键必须以 _ 开头

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String parentId;

	private Integer parentType;

	private String content;

	private Integer thumbupCount;

	private Integer commentCount;

	@JsonFormat(pattern = "yyyy-MM-dd:HH:mm:ss",timezone="GMT+8")
	private java.util.Date createdTime;

}
