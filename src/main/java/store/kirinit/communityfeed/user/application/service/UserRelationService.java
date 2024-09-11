package store.kirinit.communityfeed.user.application.service;

import org.springframework.stereotype.Service;
import store.kirinit.communityfeed.user.application.dto.FollowUserRequestDto;
import store.kirinit.communityfeed.user.application.interfaces.UserRelationRepository;
import store.kirinit.communityfeed.user.domain.User;

@Service
public class UserRelationService {
    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(
        UserService userService,
        UserRelationRepository userRelationRepository
    ) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("이미 팔로우 중입니다.");
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unFollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("팔로우 중이 아닙니다.");
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }
}
