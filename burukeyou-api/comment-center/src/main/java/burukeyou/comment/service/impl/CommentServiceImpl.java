package burukeyou.comment.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.comment.entity.pojo.AmsComment;
import burukeyou.comment.repository.CommentRepository;
import burukeyou.comment.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final MongoTemplate mongoTemplate;

    public CommentServiceImpl(CommentRepository commentRepository, MongoTemplate mongoTemplate) {
        this.commentRepository = commentRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(AmsComment amsComment) {
        Assert.notNull(amsComment,"amsComment can not be null");
        amsComment.setUserId(AuthUtils.ID());
        amsComment.setUserNickname(AuthUtils.NICKNAME());
        amsComment.setUserAvatar(AuthUtils.AVATAR());
        amsComment.setReplyCount(0);
        amsComment.setThumbupCount(0);
        amsComment.setCreatedTime(LocalDateTime.now());
        commentRepository.save(amsComment);
    }

    @Override
    public void deleteByid(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<AmsComment> getTop10ThumbupList(String type, String id){
        Page<AmsComment> pageList = getList(type,id,0, 10, Sort.Direction.DESC, "thumbupCount");
        return pageList.getContent();
    }

    @Override
    public Page<AmsComment> getLatestList(String type, String id,Integer page, Integer size){
        return getList(type,id,page,size,Sort.Direction.DESC,"createdTime");
    }

    @Override
    public AmsComment getById(String id) {
        return commentRepository.findById(id).orElse(null);
    }


    private  Page<AmsComment> getList(String type, String id,Integer page, Integer size,Sort.Direction order,String field) {
        Sort sort = new Sort(order,field);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return commentRepository.findByParentTypeAndParentId(type,id,pageRequest);
        //
       /* Query query = new Query();
        Criteria criteria = new Criteria();

        criteria.and("parentType").is(type);
        criteria.and("parentId").is(id);

        query.addCriteria(criteria);

        //
        Sort sort = new Sort(order,field);
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        List<AmsComment> amsCommentList = mongoTemplate.find(query.with(pageRequest), AmsComment.class);

        return PageableExecutionUtils.getPage(amsCommentList, pageRequest,
                () -> mongoTemplate.count(query, AmsComment.class));*/
    }
}
