package burukeyou.user.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;


@Data
@ToString
@Builder
@TableName("ums_favorites")
@NoArgsConstructor  // must implement
@AllArgsConstructor // must implement
public class UmsFavorites extends BasePojo implements Serializable {

	private String name;

	private String description;

	private String background;

	private String userId;

	private String userNickname;

	private String userAvatar;

	private Boolean ispublic;

	private Integer type;

	private Integer count;
}
