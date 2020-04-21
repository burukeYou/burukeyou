package burukeyou.system.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@TableName("sys_channel")
@AllArgsConstructor
@NoArgsConstructor
public class SysChannel extends BasePojo implements Serializable {
	private String name;
}
