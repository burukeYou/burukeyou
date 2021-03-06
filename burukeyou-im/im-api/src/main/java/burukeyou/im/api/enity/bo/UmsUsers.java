package burukeyou.im.api.enity.bo;

import burukeyou.common.dao.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UmsUsers extends BasePojo {

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

	private String lastloginHost;

	private java.util.Date lastloginTime;

	private Integer online;

	private Integer fansCount;

	private Integer followCount;

	private String blogAddress;

	private Integer exp;
}
