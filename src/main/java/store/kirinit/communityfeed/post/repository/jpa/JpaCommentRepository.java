package store.kirinit.communityfeed.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import store.kirinit.communityfeed.post.repository.entity.comment.CommentEntity;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query("UPDATE CommentEntity c "
        + "SET c.content = :#{#comment.getContent()}, "
        + "c.updDt = now() "
        + "WHERE c.id = :#{#comment.getId()}")
    void updateCommentEntity(CommentEntity comment);

    @Modifying
    @Query("UPDATE CommentEntity c "
        + "SET c.likeCount = :#{#commentEntity.getLikeCount()}, "
        + "c.updDt = now() "
        + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateLikeCount(CommentEntity commentEntity);
}
