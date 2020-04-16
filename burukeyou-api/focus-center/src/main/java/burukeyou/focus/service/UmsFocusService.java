package burukeyou.focus.service;


import burukeyou.focus.entity.pojo.UmsFocus;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
