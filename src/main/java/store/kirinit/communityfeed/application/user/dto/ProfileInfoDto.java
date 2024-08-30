package store.kirinit.communityfeed.application.user.dto;

import store.kirinit.communityfeed.domain.user.User;

public class ProfileInfoDto {
    private final Long id;
    private final String userName;
    private final String profileImageUrl;
    private final int followerCount;
    private final int followingCount;

    public ProfileInfoDto(Long id, String userName, String profileImageUrl, int followerCount, int followingCount) {
        this.id = id;
        this.userName = userName;
        this.profileImageUrl = profileImageUrl;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

    public ProfileInfoDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

}
