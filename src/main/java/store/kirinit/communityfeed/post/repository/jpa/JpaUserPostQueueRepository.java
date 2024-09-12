package store.kirinit.communityfeed.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import store.kirinit.communityfeed.post.repository.entity.post.UserPostQueueEntity;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    void deleteAllByUserIdAndWriterId(Long userId, Long writerId);
}
