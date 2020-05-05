package burukeyou.like.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeMsg implements Serializable {

	private static final long serialVersionUID = -3112993022249172121L;

	private String userId;

	private String parentId;

	private String parentType;

	boolean like;

}
