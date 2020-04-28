package burukeyou.comment.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.comment.entity.pojo.AmsReply;
import burukeyou.comment.repository.ReplyRepository;
import burukeyou.comment.service.ReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public boolean publicReply(AmsReply converTo) {
        // 所属评论回复数加一
        converTo.setCreatedTime(LocalDateTime.now());
        converTo.setThumbupCount(0);
        converTo.setUserId(AuthUtils.ID());
        converTo.setUserNickname(AuthUtils.NICKNAME());
        converTo.setUserAvatar(AuthUtils.AVATAR());
        replyRepository.save(converTo);
        return true;
    }

    @Override
    public Page<AmsReply> getReplyByCommentId(String commentId,int page,int size) {
        PageRequest of = PageRequest.of(page, size,new Sort(Sort.Direction.DESC,"createdTime"));
        return replyRepository.findByCommentId(commentId,of);
    }
}
