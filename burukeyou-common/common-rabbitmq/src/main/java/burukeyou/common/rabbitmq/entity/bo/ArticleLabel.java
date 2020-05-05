package burukeyou.common.rabbitmq.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLabel implements Serializable {

    private static final long serialVersionUID = -6957813860866360342L;

    private String articleId;

    private List<String> labelIds;
}
