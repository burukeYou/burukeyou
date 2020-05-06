package burukeyou.comment.service;

import burukeyou.comment.entity.pojo.AmsComment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    /**
     *   发表评论
     * @param amsComment
     */
    void save(AmsComment amsComment);

    /**
     *  删除评论
     * @param id
     */
    void deleteByid(String id);

    /**
     *   获得点赞最高的十条评论
     * @param type parent entity type
     * @param id parent entity id
     * @return the comment of  top 10 Thumbup count
     */
    List<AmsComment> getTop10ThumbupList(String type, String id);


    /**
     *      分页获得最新评论
     * @param type  parent entity type
     * @param id parent entity id
     * @param page current page
     * @param size every page show size
     * @return
     */
    Page<AmsComment> getLatestList(String type, String id, Integer page, Integer size);

    /**
     *  根据id获取评论
     * @param id
     * @return
     */
    AmsComment getById(String id);
}
