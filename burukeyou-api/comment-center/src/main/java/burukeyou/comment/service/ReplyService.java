package burukeyou.comment.service;

import burukeyou.comment.entity.pojo.AmsReply;
import org.springframework.data.domain.Page;

public interface ReplyService {
    /**
     *  发表回复
     * @param converTo
     * @return
     */
    boolean publicReply(AmsReply converTo);

    /**
     *     获取评论下回复
     * @param commentId
     * @return
     */
    Page<AmsReply> getReplyByCommentId(String commentId,int page,int size);
}
