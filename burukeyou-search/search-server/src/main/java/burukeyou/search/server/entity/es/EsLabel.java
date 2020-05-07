package burukeyou.search.server.entity.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EsLabel {

    private String id;

    private String name;

    private int focusCount;

    private int articleCount;

    private String createdTime;

    private String avatar;

}
