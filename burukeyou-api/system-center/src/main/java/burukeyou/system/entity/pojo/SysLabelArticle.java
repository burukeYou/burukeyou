package burukeyou.system.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLabelArticle  implements Serializable {

	private String articleId;

	private String labelId;

}
