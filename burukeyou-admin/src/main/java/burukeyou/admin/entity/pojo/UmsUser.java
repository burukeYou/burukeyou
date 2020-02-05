package burukeyou.admin.entity.pojo;

import burukeyou.common.core.entity.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ums_users")
public class UmsUser extends BasePojo implements Serializable {
	private String username;

	private String password;

	private String nickname;

	private String mobile;

	private String email;

	private String avatar;

	private String qrcode;

	private String description;

	private Boolean deleted;

	private Boolean enabled;

	private Boolean accountNonExpired;

	private Boolean credentialsNonExpired;

	private Boolean accountNonLocked;

	private String createHost;

	private java.util.Date lastloginHost;

	private java.util.Date lastloginTime;

	private Integer online;

	private Integer fansCount;

	private Integer followCount;

	private String blogAddress;

	private Integer exp;

}
