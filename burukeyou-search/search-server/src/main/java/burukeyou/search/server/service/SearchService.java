package burukeyou.search.server.service;

import burukeyou.search.server.entity.dto.QueryDto;
import burukeyou.search.server.entity.vo.SearchResultVo;

import java.util.List;

public interface SearchService {

    List<SearchResultVo> searchAll(QueryDto queryDto);
}
