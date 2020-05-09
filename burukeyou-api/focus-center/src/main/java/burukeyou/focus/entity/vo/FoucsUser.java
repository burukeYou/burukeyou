package burukeyou.focus.entity.vo;

import burukeyou.common.dao.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoucsUser  {

	private String id;

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

	private Integer fansCount;

	private Integer followCount;

	private String blogAddress;

	private Integer exp;

	private String createdBy;

	private Date createdTime;

	private String updatedBy;

	private Date updatedTime;
}
