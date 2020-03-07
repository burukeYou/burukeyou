package burukeyou.admin.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdminVO {

	private String id;

	private String nickname;

	private String mobile;

	private String email;

	private String avatar;

	private String description;

	private Boolean deleted;

	private Boolean enabled;

	private String createHost;

	private java.util.Date lastloginHost;

	private java.util.Date lastloginTime;

	//
	private String createdBy;

	private Date createdTime;

	private String updatedBy;

	private Date updatedTime;


	//
	List<UmsRoleVO> roleList;

}
