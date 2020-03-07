package burukeyou.comment.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.comment.entity.pojo.AmsComment;
import burukeyou.comment.mapper.CommentRepository;
import burukeyou.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

        //amsComment.set_id(UUID.randomUUID().toString().replace("-",""));
        amsComment.setUserId(AuthUtils.ID());
        amsComment.setUserNickname(AuthUtils.USERNAME());
        amsComment.setUserAvatar(AuthUtils.AVATAR());
        amsComment.setCommentCount(0);
        amsComment.setThumbupCount(0);
        amsComment.setCreatedTime(new Date());

        commentRepository.save(amsComment);
    }

    @Override
    public void deleteByid(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<AmsComment> getTop10ThumbupList(Integer type, String id){
        Page<AmsComment> pageList = getList(type,id,0, 10, Sort.Direction.DESC, "thumbupCount");
        return pageList.getContent();
    }

    @Override
    public Page<AmsComment> getLatestList(Integer type, String id,Integer page, Integer size){
        return getList(type,id,page,size,Sort.Direction.DESC,"createdTime");
    }


    private  Page<AmsComment> getList(Integer type, String id,Integer page, Integer size,Sort.Direction order,String field) {
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
