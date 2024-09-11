package store.kirinit.communityfeed.user.application.dto;

import store.kirinit.communityfeed.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl, Integer followingCount, Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getUserName(), user.getProfileImageUrl(), user.getFollowingCount(), user.getFollowerCount());
    }
}
