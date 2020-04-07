package burukeyou.admin.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_admin")
public class UmsAdmin extends BasePojo implements Serializable {

	private String adminName;

	private String password;

	private String nickname;

	private String mobile;

	private String email;

	private String avatar;

	private String description;

	@TableLogic
	private Boolean deleted; //逻辑删除

	private Boolean enabled;

	private Boolean accountNonExpired;

	private Boolean credentialsNonExpired;

	private Boolean accountNonLocked;

	private String createHost;

	private java.util.Date lastloginHost;

	private java.util.Date lastloginTime;

	private java.util.Date accountStartTime;

	private java.util.Date accountEndTime;
}
