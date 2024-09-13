package store.kirinit.communityfeed.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static store.kirinit.communityfeed.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static store.kirinit.communityfeed.acceptance.steps.FeedAcceptanceSteps.requestFeed;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.acceptance.utils.AcceptanceTestTemplate;
import store.kirinit.communityfeed.post.application.dto.CreatePostRequestDto;
import store.kirinit.communityfeed.post.domain.PostState;
import store.kirinit.communityfeed.post.ui.dto.GetPostContentResponseDto;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {
    /**
     * User 1 ---- follow ---> User 2
     * User 1 ---- follow ---> User 3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowingUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when, 팔로워 피드를 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }
}
