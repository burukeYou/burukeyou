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
    boolean focus(String targetId, String targetType);

    /**
     *      取消关注
     * @param targetId
     * @return
     */
    boolean cancelFocus(String targetId,String targetType);

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
}
