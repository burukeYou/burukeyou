package burukeyou.focus.service;


import burukeyou.focus.entity.pojo.UmsFocus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface UmsFocusService extends IService<UmsFocus> {

    /**
     *  关注
     * @param targetId
     * @param targetType
     * @return
     */
    boolean focus(String targetType, String targetId);

    /**
     *      取消关注
     * @param targetId
     * @return
     */
    boolean cancelFocus(String targetType,String targetId);

    /**
     *   批量判断是否关注
     * @param targetType
     * @param targetidList  id - result
     */
    Map<String,Boolean> judgeIsFollwerList(String targetType, List<String> targetidList);

    /**
     *  获取用户userId在targetType下关注的所有target
     * @param userId
     * @param targetType
     * @param page
     * @param size
     * @return
     */
    Page<String> getUserFocusTargetPage(String userId, String targetType, int page, int size);

    /**
     *  获得关注的用户id列表
     * @return
     */
    List<String> getFocusUserId();
}
