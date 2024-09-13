package store.kirinit.communityfeed.acceptance.utils;

import static store.kirinit.communityfeed.acceptance.steps.UserAcceptanceSteps.createUser;
import static store.kirinit.communityfeed.acceptance.steps.UserAcceptanceSteps.followUser;

import org.springframework.stereotype.Component;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.application.dto.FollowUserRequestDto;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
