package burukeyou.user.controller;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.common.core.utils.ValidationUtils;
import burukeyou.user.entity.dto.UmsColumnDto;
import burukeyou.user.entity.enums.QueryTypeEnum;
import burukeyou.user.entity.pojo.UmsColumn;
import burukeyou.user.entity.vo.UmsColumnVo;
import burukeyou.user.service.UserColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  思考：
 *       //@PreAuthorize("principal.username.equals(#username)")
 *
 *        // only user of umsColumn can delete umsColumn
 *         if (!this.getOne(new QueryWrapper<UmsColumn>().select("user_id").eq("id",id)).getUserId().equals(AuthUtils.ID()))
 *             return false;
 *
 *         根据id查找   --》  用户可以查找别人
 *         根据id删除   --》  用户不可以删除别人
 *
 *
 *
 */

@Api("用户个人专栏")
@RestController
@RequestMapping("/column")
public class UserColumnController {

    private final UserColumnService userColumnService;

    public UserColumnController(UserColumnService userColumnService) {
        this.userColumnService = userColumnService;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "保存或者修改当前用户的专栏",notes = "默认修改或者保存当前登陆用户的专栏")
    @ApiImplicitParam(name = "umsColumnDto",value = "新建或者更新专栏信息",required = true,dataType = "UmsColumnDto" )
    public ResultVo add(@RequestBody UmsColumnDto umsColumnDto){
        return ResultVo.compute(userColumnService.insertOrUpdate(umsColumnDto.converTo()));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("principal.username.equals(#username)") 直接设计成删除当前用户的，因为这种接口只能自己调用
    @ApiOperation(value = "根据专栏id删除专栏")
    @ApiImplicitParam(name = "id",value = "专栏id",required = true,dataType = "String")
    public ResultVo delete(@PathVariable("id")  String id){
        return ResultVo.compute(userColumnService.deleteById(id));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查找专栏详情")
    public ResultVo getOne(@PathVariable("id") String id){
            return ResultVo.success(userColumnService.getById(id));
    }


    @GetMapping("/page/{userId}")
    @ApiOperation("查找某个用户的所有专栏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String")
    })
    public ResultVo getList(@PathVariable(value = "userId") String userId,@RequestParam("page") int page,@RequestParam("size")int size){
       return ResultVo.success(userColumnService.getPageByUserId(userId,page,size));
    }


}
