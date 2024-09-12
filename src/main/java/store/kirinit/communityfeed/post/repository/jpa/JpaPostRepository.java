package store.kirinit.communityfeed.post.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p.id FROM PostEntity p WHERE p.writer.id = :writerId")
    List<Long> findALlPostIdsByWriterId(Long writerId);

    @Modifying
    @Query("UPDATE PostEntity p "
        + "SET p.content = :#{#postEntity.content}, "
        + "p.state = :#{#postEntity.state}, "
        + "p.updDt = now() "
        + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("UPDATE PostEntity p "
        + "SET p.likeCount = :#{#postEntity.getLikeCount()}, "
        + "p.updDt = now() "
        + "WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query("UPDATE PostEntity p "
        + "SET p.commentCount = p.commentCount + 1, "
        + "p.updDt = now() "
        + "WHERE p.id = :id")
    void increaseCommentCount(Long id);
}
