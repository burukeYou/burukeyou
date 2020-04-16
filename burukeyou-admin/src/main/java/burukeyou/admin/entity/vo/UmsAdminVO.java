package burukeyou.admin.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

	private String lastloginHost;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private LocalDateTime lastloginTime;

	//
	private String createdBy;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private LocalDateTime createdTime;

	private String updatedBy;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private LocalDateTime updatedTime;


	//
	List<UmsRoleVO> roleList;

}
