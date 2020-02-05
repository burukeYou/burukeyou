package burukeyou.admin.entity.pojo;

import burukeyou.common.core.entity.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_menu")
public class UmsMenu extends BasePojo implements Serializable {

	private String parentId;

	private String type;

	private String href;

	private String icon;

	private String name;

	private String description;

	private Long orderNum;
}
