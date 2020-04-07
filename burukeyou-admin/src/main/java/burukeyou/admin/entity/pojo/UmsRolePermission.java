package burukeyou.admin.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsRolePermission extends BasePojo implements Serializable {

	private String roleId;

	private String permission_id;

}
