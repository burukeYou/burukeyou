package burukeyou.user.controller;

import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.user.entity.dto.UmsFavoritesDto;
import burukeyou.user.entity.enums.QueryTypeEnum;
import burukeyou.user.entity.pojo.UmsFavorites;
import burukeyou.user.entity.vo.UmsColumnVo;
import burukeyou.user.entity.vo.UmsFavoritesListVo;
import burukeyou.user.service.UmsFavoritesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 *   用户收藏管理控制器
 *
 * @author burukeyou
 */
@RestController
@RequestMapping("/user/collection")
@Api("用户收藏")
@Slf4j
public class UmsFavoritesController {

    private final UmsFavoritesService umsFavoritesService;

    public UmsFavoritesController(UmsFavoritesService umsFavoritesService) {
        this.umsFavoritesService = umsFavoritesService;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "创建收藏夹")
    @ApiImplicitParam(name = "umsFavoritesDto",value = "收藏夹基本信息",required = true,dataType = "UmsFavoritesDto")
    public ResultVo save(UmsFavoritesDto umsFavoritesDto){

        // todo 使用rabbitmq 异步化处理上传，上传后返回文件url
        MultipartFile multipartFile = umsFavoritesDto.getBackgroundFile();

        if (multipartFile != null)
            umsFavoritesDto.setBackground(multipartFile.getName());

        return ResultVo.compute(umsFavoritesService.insertOrUpdate(umsFavoritesDto.converTo()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据收藏夹id删除专栏,用户只能删除属于自己专收藏夹的id")
    @ApiImplicitParam(name = "id",value = "专栏id",required = true,dataType = "String")
    public ResultVo delete(@PathVariable("id")  String id){
        return ResultVo.compute(umsFavoritesService.deleteById(id));
    }


    @GetMapping("/{type}/list/{userId}")
    @ApiOperation(value = "查找用户的所有收藏夹列表",notes = "非收藏拥有者只能查找公开的收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "type",value = "返回的专栏信息类型(前台，后台)",required = true,dataType = "String"),
    })
    public ResultVo getListByUserId(@PathVariable(value = "type") String type,@PathVariable(value = "userId") String userId){
        if (QueryTypeEnum.FrontDesk.getType().equals(type)){
            return ResultVo.success( umsFavoritesService.getListByUserId(userId).stream()
                    .map(e->new UmsFavoritesListVo().convertFrom(e)).collect(Collectors.toList()));
        }else if (QueryTypeEnum.Admin.getType().equals(type)){
            return ResultVo.success(umsFavoritesService.getListByUserId(userId));
        }else
            return ResultVo.success();
    }


    @GetMapping("/{type}/{id}")
    @ApiOperation(value = "根据id查找收藏夹",notes = "如果是查找别人专栏只能是公开的专栏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "返回的专栏信息类型(前台，后台)",required = true,dataType = "String"),
            @ApiImplicitParam(name = "id",value = "专栏id",required = true,dataType = "String")
    })
    public ResultVo getOne(@PathVariable("type") String type,@PathVariable("id") String id) {
        if (QueryTypeEnum.FrontDesk.getType().equals(type)) {
            return ResultVo.success(new UmsFavoritesListVo().convertFrom(umsFavoritesService.getById(id)));
        } else if (QueryTypeEnum.Admin.getType().equals(type)) {
            return ResultVo.success(umsFavoritesService.getById(id));
        } else
            return ResultVo.success();
    }


    @GetMapping("/judge/{collectionType}/{collectionId}")
    @ApiOperation("判断是否收藏了该target")
    public String judgeIsFavorities(@PathVariable("collectionType") String collectionType,@PathVariable("collectionId") String collectionId){
        return favoritiesCollectionService.judgeIsFavorities(collectionType,collectionId);
    }

    @PostMapping("/{favoritiesId}/{collectionType}/{collectionId}")
    @ApiOperation("添加进收藏夹")
    public ResultVo addCollection(@NotBlank @PathVariable("favoritiesId") String favoritiesId,
                                  @NotBlank  @PathVariable("collectionType") String collectionType,
                                  @NotBlank  @PathVariable("collectionId") String collectionId){
        return ResultVo.compute(favoritiesCollectionService.addCollection(favoritiesId,collectionType,collectionId));
    }

    @DeleteMapping("/{favoritiesId}/{collectionType}/{collectionId}")
    @ApiOperation("取消收藏")
    public ResultVo cancelCollection(@NotBlank  @PathVariable("favoritiesId") String favoritiesId,
                                     @NotBlank  @PathVariable("collectionType") String collectionType,
                                     @NotBlank  @PathVariable("collectionId") String collectionId){
        return ResultVo.compute(favoritiesCollectionService.removeCollection(favoritiesId,collectionType,collectionId));
    }

    @GetMapping("/getFavoritiesId")
    @ApiOperation("获取收藏品所属收藏夹id")
    public ResultVo<String> getFavoritiesByCollection(@RequestParam("collectionId") String collectionId,@RequestParam("collectionType") String collectionType){
        FavoritiesCollection one = favoritiesCollectionService.getOne(new QueryWrapper<FavoritiesCollection>().lambda()
                                                                .select(FavoritiesCollection::getFavoritesId)
                                                                .eq(FavoritiesCollection::getCollectionId, collectionId)
                                                                .eq(FavoritiesCollection::getCollectionType, collectionType));
        return ResultVo.success(one.getFavoritesId());
    }

}
