package burukeyou.system.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysTopicCategoryShow  implements Serializable {

	private String id;

	private String name;

	private Short isshow;

	private java.util.Date createdTime;

	private java.util.Date updatedTime;

	private String createdBy;

	private String updatedBy;

}
