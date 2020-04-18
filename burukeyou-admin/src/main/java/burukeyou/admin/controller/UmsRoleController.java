package burukeyou.admin.controller;

import burukeyou.admin.entity.dto.RoleDto;
import burukeyou.admin.entity.pojo.UmsRole;
import burukeyou.admin.service.UmsRolePermissionService;
import burukeyou.admin.service.UmsRoleService;
import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api("角色管理")
@RequestMapping("/role")
public class UmsRoleController {

    private final UmsRoleService umsRoleService;

    private final UmsRolePermissionService umsRolePermissionService;


    public UmsRoleController(UmsRoleService umsRoleService, UmsRolePermissionService umsRolePermissionService) {
        this.umsRoleService = umsRoleService;
        this.umsRolePermissionService = umsRolePermissionService;
    }

    @GetMapping("user/{userId}")
    @ApiOperation(value = "查询角色", notes = "根据后台用户id查询用户所拥有的所有角色")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "long")
    @ApiResponse(code = 200, message = "处理成功", response = ResultVo.class)
    public ResultVo getAllRoleByUserId(@PathVariable String userId){
        log.info("query with userId:{}", userId);
        return ResultVo.success(umsRoleService.getByUserId(userId));
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "新增或者修改角色信息")
    public ResultVo saveOrUpdate(@RequestBody RoleDto roleDto){
        return ResultVo.compute(umsRoleService.addOrUpdate(roleDto.converTo()));
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id",value = "角色id",dataType = "String")
    public ResultVo deleteById(@PathVariable String roleId){
        return ResultVo.compute(umsRoleService.deleteRoleById(roleId));
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页获取角色列表")
    public ResultVo list(@RequestParam int page,@RequestParam int  size){
        Page<UmsRole> pageList  = umsRoleService.getPage(page,size);
        return ResultVo.success(pageList);
    }

    @PostMapping("{roleId}/menu")
    @ApiOperation(value = "给角色分配菜单权限")
    public ResultVo allocatAuthority(@PathVariable String roleId, List<String> permission){
        permission.stream().forEach(e-> umsRolePermissionService.saveRolePermission(roleId,e));
        return ResultVo.success();
    }

    @GetMapping("/all")
    @ApiOperation(value = "查找所有角色列表")
    public ResultVo all(){
        return ResultVo.success(umsRoleService.list());
    }


    @GetMapping("/{roleId}")
    @ApiOperation("查看角色详情")
    public ResultVo getOneById(@PathVariable String roleId){
        return ResultVo.success(umsRoleService.getById(roleId));
    }






}
