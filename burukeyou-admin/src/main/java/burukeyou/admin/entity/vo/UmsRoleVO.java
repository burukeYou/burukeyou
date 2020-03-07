package burukeyou.admin.entity.vo;

import burukeyou.admin.entity.pojo.UmsRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder

public class UmsRoleVO {

	private String id;

	private String code;

	private String name;

	private String description;
}
