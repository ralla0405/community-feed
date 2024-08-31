package store.kirinit.communityfeed.user.application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.application.dto.FollowUserRequestDto;
import store.kirinit.communityfeed.user.application.interfaces.UserRelationRepository;
import store.kirinit.communityfeed.user.application.interfaces.UserRepository;
import store.kirinit.communityfeed.user.application.repository.FakeUserRelationRepository;
import store.kirinit.communityfeed.user.application.repository.FakeUserRepository;
import store.kirinit.communityfeed.user.domain.User;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;
    
    @BeforeEach
    void init() {
        this.user1 = userService.createUser(new CreateUserRequestDto("user1", ""));
        this.user2 = userService.createUser(new CreateUserRequestDto("user2", ""));

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void 팔로우_서비스_테스트() throws Exception {
        // given
        // when
        userRelationService.follow(requestDto);
        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void 팔로우_중복_서비스_테스트() throws Exception {
        // given
        // when
        userRelationService.follow(requestDto);
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void 스스로_팔로우_테스트() throws Exception {
        // given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(), user1.getId());
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(dto));
    }

    @Test
    void 언팔로우_서비스_테스트() throws Exception {
        // given
        userRelationService.follow(requestDto);
        // when
        userRelationService.unFollow(requestDto);
        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void 언팔로우_중복_서비스_테스트() throws Exception {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(requestDto));
    }

    @Test
    void 스스로_언팔로우_테스트() throws Exception {
        // given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(), user1.getId());
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(dto));
    }
}