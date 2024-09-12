package store.kirinit.communityfeed.post.repository.post_queue;

import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {
    void publishPost(PostEntity postEntity);
    void saveFollowPost(Long userId, Long targetId);
    void deleteUnfollowPost(Long userId, Long targetId);
}
