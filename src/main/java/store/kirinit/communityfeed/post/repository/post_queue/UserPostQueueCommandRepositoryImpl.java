package store.kirinit.communityfeed.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;
import store.kirinit.communityfeed.post.repository.jpa.JpaPostRepository;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;
import store.kirinit.communityfeed.user.repository.jpa.JpaUserRelationRepository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserQueueRedisRepository redisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getWriter();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        redisRepository.publishPostToFollowingUserList(postEntity,  followerIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostByWriterId(targetId);
        redisRepository.publishPostListToFollowerUser(postEntities, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        redisRepository.deleteDeleteFeed(userId, targetId);
    }
}
