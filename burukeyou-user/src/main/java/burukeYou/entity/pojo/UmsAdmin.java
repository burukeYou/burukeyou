package burukeYou.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import common.entity.pojo.BasePojo;
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

	private Short deleted;

	private Short enabled;

	private Short accountNonExpired;

	private Short credentialsNonExpired;

	private Short accountNonLocked;

	private String createHost;

	private java.util.Date lastloginHost;

	private java.util.Date lastloginTime;
}
