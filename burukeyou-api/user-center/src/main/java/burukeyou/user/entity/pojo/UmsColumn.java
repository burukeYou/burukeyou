package burukeyou.user.entity.pojo;

import burukeyou.common.core.entity.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@TableName("ums_column")
@NoArgsConstructor  // must implement
@AllArgsConstructor // must implement
public class UmsColumn extends BasePojo implements Serializable {

	private String name;

	private String summary;

	private String image;

	private String userId;

	private String username;

	private Integer article_count;

	private Boolean ispublic; // false - 0 , true - 1

	private Boolean istop;

	private Integer state;
}
