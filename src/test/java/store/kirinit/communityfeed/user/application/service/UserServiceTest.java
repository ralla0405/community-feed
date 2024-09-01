package store.kirinit.communityfeed.user.application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.fake.FakeObjectFactory;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.domain.User;

class UserServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void 유저_저장_테스트() throws Exception {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("abc", "");
        // when
        User savedUser = userService.createUser(dto);
        // then
        User findUser = userService.getUser(savedUser.getId());
        assertEquals(findUser.getId(), savedUser.getId());
        assertEquals(findUser.getUserName(), savedUser.getUserName());
    }
}