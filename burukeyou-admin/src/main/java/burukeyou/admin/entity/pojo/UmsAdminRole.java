package burukeyou.admin.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ums_admin_role")
public class UmsAdminRole extends BasePojo implements Serializable {

	private String userId;

	private String roleId;
}
