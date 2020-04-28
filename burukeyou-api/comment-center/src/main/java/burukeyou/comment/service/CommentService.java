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

    void deleteByid(String id);

    /**
     *   获得点赞最高的十条评论
     * @param type parent entity type
     * @param id parent entity id
     * @return the comment of  top 10 Thumbup count
     */
    List<AmsComment> getTop10ThumbupList(String type, String id);


    /**
     *
     * @param type  parent entity type
     * @param id parent entity id
     * @param page current page
     * @param size every page show size
     * @return
     */
    Page<AmsComment> getLatestList(String type, String id, Integer page, Integer size);

    AmsComment getById(String id);
}
