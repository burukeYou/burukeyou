package burukeyou.comment.repository;

import burukeyou.comment.entity.pojo.AmsReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository  extends MongoRepository<AmsReply,String> {

    Page<AmsReply> findByCommentId(String commentId, Pageable of);
}
