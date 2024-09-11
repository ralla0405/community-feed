package store.kirinit.communityfeed.user.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.kirinit.communityfeed.common.ui.Response;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.application.dto.GetUserListResponseDto;
import store.kirinit.communityfeed.user.application.dto.GetUserResponseDto;
import store.kirinit.communityfeed.user.application.service.UserService;
import store.kirinit.communityfeed.user.domain.User;
import store.kirinit.communityfeed.user.repository.jpa.JpaUserListQueryRepository;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto requestDto) {
        User user = userService.createUser(requestDto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowing(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollower(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }
}
