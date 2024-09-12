package store.kirinit.communityfeed.user.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.kirinit.communityfeed.user.repository.entity.UserRelationEntity;
import store.kirinit.communityfeed.user.repository.entity.UserRelationIdEntity;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    @Query("SELECT u.followingUserId FROM UserRelationEntity u WHERE u.followerUserId = :userId")
    List<Long> findFollowers(Long userId);
}
