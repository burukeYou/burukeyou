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
@TableName("ums_resource")
public class UmsResource extends BasePojo implements Serializable {

	private String code;

	private String type;

	private String name;

	private String url;

	private String method;

	private String description;
}
