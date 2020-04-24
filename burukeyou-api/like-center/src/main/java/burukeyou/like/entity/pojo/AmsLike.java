package burukeyou.like.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("ams_like")
@AllArgsConstructor
@NoArgsConstructor
public class AmsLike  implements Serializable {

	private String id;

	private String userId;

	private String parentId;

	private String parentType;

}
