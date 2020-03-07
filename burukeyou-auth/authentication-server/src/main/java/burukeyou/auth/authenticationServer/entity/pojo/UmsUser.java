package burukeyou.auth.authenticationServer.entity.pojo;

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
