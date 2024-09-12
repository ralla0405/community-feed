package store.kirinit.communityfeed.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;
import store.kirinit.communityfeed.post.repository.entity.post.UserPostQueueEntity;
import store.kirinit.communityfeed.post.repository.jpa.JpaPostRepository;
import store.kirinit.communityfeed.post.repository.jpa.JpaUserPostQueueRepository;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;
import store.kirinit.communityfeed.user.repository.jpa.JpaUserRelationRepository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getWriter();
        List<Long> followers = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntityList = followers.stream()
            .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
            .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIdList = jpaPostRepository.findALlPostIdsByWriterId(targetId);
        List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream()
            .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
            .toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndWriterId(userId, targetId);
    }
}
