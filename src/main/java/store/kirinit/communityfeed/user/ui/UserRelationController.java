package store.kirinit.communityfeed.user.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.kirinit.communityfeed.common.ui.Response;
import store.kirinit.communityfeed.user.application.dto.FollowUserRequestDto;
import store.kirinit.communityfeed.user.application.service.UserRelationService;

@RestController
@RequestMapping("relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.unFollow(dto);
        return Response.ok(null);
    }

}
