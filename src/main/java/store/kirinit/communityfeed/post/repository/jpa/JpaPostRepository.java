package store.kirinit.communityfeed.post.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.writer.id = :writerId")
    List<PostEntity>  findAllPostByWriterId(Long writerId);

    @Modifying
    @Query("UPDATE PostEntity p "
        + "SET p.content = :#{#postEntity.content}, "
        + "p.state = :#{#postEntity.state}, "
        + "p.updDt = now() "
        + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("UPDATE PostEntity p "
        + "SET p.likeCount = p.likeCount + :likeCount, "
        + "p.updDt = now() "
        + "WHERE p.id = :postId")
    void updateLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query("UPDATE PostEntity p "
        + "SET p.commentCount = p.commentCount + 1, "
        + "p.updDt = now() "
        + "WHERE p.id = :id")
    void increaseCommentCount(Long id);
}
