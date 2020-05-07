package burukeyou.search.server.controller;


import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.search.server.entity.dto.QueryDto;
import burukeyou.search.server.service.SearchService;
import burukeyou.search.server.temp.SysLabel;
import burukeyou.search.server.temp.SysLableServiceImpl;
import io.swagger.annotations.Api;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/search")
@Api(value = "全局搜索接口")
public class GlobalController {

    private final SearchService searchService;


    public GlobalController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResultVo search(QueryDto queryDto){
        return ResultVo.success(searchService.searchAll(queryDto));
    }



}
