package store.kirinit.communityfeed.post.repository.post_queue;

import java.util.List;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;

public interface UserQueueRedisRepository {
    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowerUser(List<PostEntity> postEntities, Long userId);
    void deleteDeleteFeed(Long userId, Long writerId);
}
