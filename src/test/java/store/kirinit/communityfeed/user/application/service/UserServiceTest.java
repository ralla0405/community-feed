package store.kirinit.communityfeed.user.application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.application.interfaces.UserRepository;
import store.kirinit.communityfeed.user.application.repository.FakeUserRepository;
import store.kirinit.communityfeed.user.domain.User;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void 유저_저장_테스트() throws Exception {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("abc", "");
        // when
        User savedUser = userService.createUser(dto);
        System.out.println("savedUser.getId() = " + savedUser.getId());
        // then
        User findUser = userService.getUser(savedUser.getId());
        assertEquals(findUser.getId(), savedUser.getId());
        assertEquals(findUser.getUserName(), savedUser.getUserName());
    }
}