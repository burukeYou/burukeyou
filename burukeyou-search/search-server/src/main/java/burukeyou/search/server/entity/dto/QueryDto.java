package burukeyou.search.server.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {

    private String type; // all or index

    private String keyword;

    private int page  = 0;

    private int size = 10;



}
