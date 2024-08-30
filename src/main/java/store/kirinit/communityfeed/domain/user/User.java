package store.kirinit.communityfeed.domain.user;

import java.util.List;
import store.kirinit.communityfeed.domain.feed.Feed;
import store.kirinit.communityfeed.domain.feed.FeedList;

public class User {
    private final Long id;
    private final String userName;
    private final ProfileImageVo profileImageUrl;
    private final FollowerList followers;
    private final FollowingList followings;
    private final FeedList feeds;

    public User(Long id, String userName, String profileImageUrl) {
        if (id == null) {
            throw new IllegalArgumentException("id는 필수 입력값입니다.");
        }
        this.id = id;
        this.userName = checkUserName(userName);
        this.profileImageUrl = new ProfileImageVo(profileImageUrl);
        this.followers = new FollowerList();
        this.followings = new FollowingList();
        this.feeds = new FeedList();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl.profileImageUrl();
    }

    public int getFollowerCount() {
        return followers.getFollowerCount();
    }

    public List<User> getFollowers() {
        return followers.getFollowers();
    }

    public int getFollowingCount() {
        return followings.getFollowingCount();
    }

    public void follow(User user) {
        if (user.equals(this)) {
            throw new IllegalArgumentException("자신을 팔로우할 수 없습니다.");
        }
        followings.addFollowing(user);
        user.followers.addFollower(this);
    }

    public void unfollow(User user) {
        if (user.equals(this)) {
            throw new IllegalArgumentException("자신을 언팔로우할 수 없습니다.");
        }
        followings.removeFollowing(user);
        user.followers.removeFollower(this);
    }

    public List<User> getFollowings() {
        return followings.getFollowings();
    }

    public List<Feed> getFeeds() {
        return feeds.getFeeds();
    }

    public void changeProfileImage(String profileImageUrl) {
        this.profileImageUrl.changeProfileImageUrl(profileImageUrl);
    }

    private String checkUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("userName은 필수 입력값입니다.");
        }
        return userName;
    }

}
