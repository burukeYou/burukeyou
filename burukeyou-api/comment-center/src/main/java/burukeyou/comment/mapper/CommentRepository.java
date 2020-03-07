package burukeyou.comment.mapper;

import burukeyou.comment.entity.pojo.AmsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends MongoRepository<AmsComment,String> {

    Page<AmsComment> findByParentTypeAndParentId(@Param("parentType") Integer parentType, @Param("parentId") String parentId, Pageable pageable);

}
