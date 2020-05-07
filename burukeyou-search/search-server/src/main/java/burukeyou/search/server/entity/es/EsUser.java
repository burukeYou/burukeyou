package burukeyou.search.server.entity.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsUser {

    private String id;

    private String nickname;

    private String description;

    private int fansCount;

    private int followCount;

    private String avatar;

}
