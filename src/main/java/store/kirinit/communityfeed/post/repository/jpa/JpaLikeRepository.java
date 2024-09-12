package store.kirinit.communityfeed.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import store.kirinit.communityfeed.post.repository.entity.like.LikeEntity;
import store.kirinit.communityfeed.post.repository.entity.like.LikeIdEntity;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

}
