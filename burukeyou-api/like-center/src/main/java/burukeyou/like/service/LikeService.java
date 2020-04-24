package burukeyou.like.service;

import burukeyou.like.entity.pojo.AmsLike;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


public interface LikeService extends IService<AmsLike> {

    /**
     *      批量判断当前用户是否对parentType下的parentIdList是否点赞过
     * @param parentType
     * @param parentIdList
     * @return
     */
    Map<String,Boolean> judgeIsLikeList(String parentType, List<String> parentIdList);
}
