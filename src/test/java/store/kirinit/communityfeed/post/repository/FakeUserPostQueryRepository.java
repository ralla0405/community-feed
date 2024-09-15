package store.kirinit.communityfeed.post.repository;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;
import store.kirinit.communityfeed.post.repository.post_queue.UserPostQueueQueryRepository;
import store.kirinit.communityfeed.post.ui.dto.GetPostContentResponseDto;

@Repository
@Profile("test")
@RequiredArgsConstructor
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    @Override
    public List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId) {

        List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostListByUserId(
            userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity: postEntities) {
            result.add(GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .build());
        }
        return result;
    }
}
