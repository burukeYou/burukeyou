package burukeyou.focus.service;

import burukeyou.focus.entity.dto.FocusCount;
import burukeyou.focus.entity.pojo.UmsFocus;

import java.util.List;

public interface RedisFocusService {

    /**
     *      关注
     * @param userId
     * @param targetId
     * @param targetType
     */
    void focus(String userId,String targetId,String targetType);

    /**
     *  取消关注
     * @param userId
     * @param targetId
     * @param targetType
     */
    void cancelFocus(String userId,String targetId,String targetType);

    /**
     *  获取所有关注状态数据
     */
    List<UmsFocus> getAllFocusData();

    /**
     *   获得所有关注target的数量数据
     */
    List<FocusCount>  getAllFoucusCountData();


    /**
     *     同步关注状态数据到DB
     */
    void focusStatusDataSyncToDB();

    /**
     *      同步关注数量同步到DB
     */
    void focusCountDataSyncToDB();

    /**
     *  判断当前用户是否有对target的关注状态
     * @param targetType
     * @param targetId
     * @return
     */
    boolean judgeIsHaveFocus(String targetType,String targetId);
}
