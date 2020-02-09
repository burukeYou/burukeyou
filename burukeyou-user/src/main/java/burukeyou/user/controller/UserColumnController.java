package burukeyou.user.controller;

import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.common.core.utils.ValidationUtils;
import burukeyou.user.entity.dto.UmsColumnDto;
import burukeyou.user.service.UserColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api("用户个人专栏")
@RestController
@RequestMapping("/user/column")
public class UserColumnController {

    private final UserColumnService userColumn;

    public UserColumnController(UserColumnService userColumn) {
        this.userColumn = userColumn;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "保存或者修改用户专栏")
    @ApiImplicitParam(name = "umsColumnDto",value = "新建或者更新专栏信息",required = true,dataType = "UmsColumnDto" )
    public ResultVo add(@RequestBody UmsColumnDto umsColumnDto){
        return ResultVo.compute(userColumn.saveOrupdate(umsColumnDto.converTo()));
    }


















}
