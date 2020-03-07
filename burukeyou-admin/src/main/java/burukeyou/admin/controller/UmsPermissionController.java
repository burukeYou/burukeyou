package burukeyou.admin.controller;

import burukeyou.admin.entity.bo.Trie;
import burukeyou.admin.entity.bo.menu.BaseMenu;
import burukeyou.admin.entity.dto.UmsPermissionDto;
import burukeyou.admin.entity.enums.PermissionTypeEnum;
import burukeyou.admin.entity.vo.MenuTreeVO;
import burukeyou.admin.utils.TreeUtil;
import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.admin.entity.vo.MenuBaseVO;
import burukeyou.admin.service.PermissionService;
import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Api("菜单管理")
@RequestMapping("/permission")
@RestController
public class UmsPermissionController {

    private static  AtomicInteger count = new AtomicInteger(0);
   // private static  int count = 0;

    private static AtomicInteger sum = new AtomicInteger(0);

    @Autowired
    private PermissionService permissionService;


    @GetMapping("/all/meun")
    @ApiOperation(value = "获得所有菜单列表")
    public ResultVo getALlPermission(){
        List<UmsPermission> list = permissionService.getPermissionByRoleId(null, PermissionTypeEnum.MENU.Type());

        Trie<MenuTreeVO> trie = new Trie<>();
        for (UmsPermission e : list) { trie.addNode(e.getPath(),new MenuTreeVO().convertFrom(e)); }

        List<MenuTreeVO> menuTreeVO = trie.converTo();

        return ResultVo.success(menuTreeVO);
    }



    @GetMapping("/{id}")
    @ApiOperation(value = "获得菜单详情")
    public ResultVo getById(@PathVariable("id") String id){
        return ResultVo.success(permissionService.getById(id));
    }


    @GetMapping("/{roleId}/tree")
    @ApiOperation(value = "根据角色id获取权限树")
    public ResultVo getPermissionTreeByRoleId(@PathVariable String roleId){
        List<UmsPermission> list = permissionService.getPermissionByRoleId(roleId, null);
        List<MenuBaseVO> voList = list.stream().map(e -> { MenuBaseVO vo = new MenuBaseVO().convertFrom(e);return vo;}).collect(Collectors.toList());

        //
        Map<String, MenuBaseVO> permissionMap = voList.stream().collect(Collectors.toMap(BaseMenu::getId, MenuBaseVO -> MenuBaseVO));

        //
        List<UmsPermission> allMenu = permissionService.list();
        for (UmsPermission e : allMenu) {
            if (permissionMap.containsKey(e.getId())){
                permissionMap.get(e.getId()).setChecked(true);
            }
        }

        //
        List<MenuBaseVO> tree = TreeUtil.bulid(voList, "-1");
        return ResultVo.success(tree);
    }


    @GetMapping("/tree")
    @ApiOperation("获取当前用户的菜单树")
    public ResultVo getAllCurrentUserMenu(){
        List<UmsPermission> list = permissionService.getPermissionByUserId(AuthUtils.ID(), PermissionTypeEnum.MENU.Type());
        return ResultVo.success(list);
    }


    @PostMapping("/{roleId}")
    @ApiOperation(value = "给角色分配权限")
    public ResultVo setPermissionOfRole(@PathVariable String roleId,@RequestBody List<String>  permissionIds){
        return ResultVo.compute(permissionService.setPermissionOfRole(roleId,permissionIds));
    }


    //  87850 49142 65916 43116  72892   // 递归： 23069 137933 61712 47978
    private void test02(){
        //count.getAndIncrement();
      /*  long l = System.currentTimeMillis();
        List<UmsPermission> list = menuService.list();
        List<MenuBaseVO> voList = list.stream().map(e -> {
            MenuBaseVO menuNodeVO = new MenuBaseVO();
            BeanUtils.copyProperties(e, menuNodeVO);
            return menuNodeVO;
        }).collect(Collectors.toList());

        List<MenuBaseVO> bulid = TreeUtil.bulid(voList, "-1");
        //List<MenuNodeVO> bulid =TreeUtil.buildByRecursive(voList,"-1");

        long res = System.currentTimeMillis() - l;
        System.out.println((count.incrementAndGet())+".当前请求运行时间："+res+"===>"+(sum.addAndGet((int)res))); //87 18 8 8    / 86 13 10 7 8
        return bulid;*/
    }

    //   30860 22972 32203 //14056 26628 18760      // 41552  17985   //new 75374 44358 41790
    private Object test1(){
        long l = System.currentTimeMillis();
        List<UmsPermission> list = permissionService.list();
        Trie<MenuTreeVO> trie = new Trie<>();

        //list.stream().forEach(e-> trie.addNode(e.getPath(),new MenuTreeVO().convertFrom(e)));

        for (UmsPermission e : list) { trie.addNode(e.getPath(),new MenuTreeVO().convertFrom(e)); }

        List<MenuTreeVO> menuTreeVO = trie.converTo();

        long res = System.currentTimeMillis() - l;
        System.out.println((count.incrementAndGet())+".当前请求运行时间："+res+"===>"+(sum.addAndGet((int)res))); //87 18 8 8    / 86 13 10 7 8
        return menuTreeVO;
    }



    @PostMapping
    @ApiOperation("新增或者修改权限信息")
    @ApiImplicitParam(name = "umsPermissionDto",value = "权限",required = true,dataType = "umsPermissionDto")
    public ResultVo saveOrupdate(@RequestBody UmsPermissionDto umsPermissionDto){
        return ResultVo.compute(permissionService.save(umsPermissionDto.converTo()));
    }

    @DeleteMapping("/{id}")
    public ResultVo deleteById(@PathVariable("id") String id){
        return ResultVo.compute(permissionService.deleteById(id));
    }
}
