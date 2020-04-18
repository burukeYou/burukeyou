package burukeyou.admin.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
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
@TableName("ums_permission")
public class UmsPermission extends BasePojo implements Serializable {

	private String name;

	private String parentId;

	private String code;

	private String type;

	private String icon;

	private String url;

	private String urlMethod;

	private String description;

	private boolean disabled;

	private Integer orderNum;

	private boolean isshow; //  mysql 关键字 show

	private String path;

}
