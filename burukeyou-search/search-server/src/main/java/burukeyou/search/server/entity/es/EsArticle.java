package burukeyou.search.server.entity.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsArticle {

    private String id;

    private String title;

    private String description;

    private String content;

    private String htmlContent;

    private int visitsCount;

    private int thumbupCount;

    private int commentCount;

    private String createdTime;
}
