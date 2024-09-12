package store.kirinit.communityfeed.post.repository.post_queue;

import java.util.List;
import store.kirinit.communityfeed.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId);
}
