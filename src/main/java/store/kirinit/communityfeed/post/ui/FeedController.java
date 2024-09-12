package store.kirinit.communityfeed.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.kirinit.communityfeed.common.ui.Response;
import store.kirinit.communityfeed.post.repository.post_queue.UserPostQueueQueryRepository;
import store.kirinit.communityfeed.post.ui.dto.GetPostContentResponseDto;

@RestController
@RequestMapping("feeds")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeeds(@PathVariable(name = "userId") Long userId, Long lastPostId) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getPostList(userId,
            lastPostId);
        return Response.ok(result);
    }
}
