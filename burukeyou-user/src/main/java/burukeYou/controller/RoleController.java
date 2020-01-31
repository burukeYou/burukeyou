package burukeYou.controller;

import burukeYou.service.RoleService;
import common.entity.vo.ResultVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api("role")
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "查询角色", notes = "根据后台用户id查询用户所拥有的所有角色的英文名称")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "long")
    @ApiResponse(code = 200, message = "处理成功", response = ResultVo.class)
    @GetMapping("user/{userId}")
    public ResultVo getAllRoleByUserId(@PathVariable String userId){
        log.info("query with userId:{}", userId);
        return ResultVo.success(roleService.getByUserId(userId));
    }


}
