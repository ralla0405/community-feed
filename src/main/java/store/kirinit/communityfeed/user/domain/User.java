package store.kirinit.communityfeed.user.domain;

import java.util.List;
import java.util.Objects;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.PostList;

public class User {
    private final Long id;
    private final UserInfo info;
    private final UserFollow followers;
    private final UserFollow followings;
    private final PostList feeds;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.info = userInfo;
        this.followers = new UserFollow();
        this.followings = new UserFollow();
        this.feeds = new PostList();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return info.getName();
    }

    public String getProfileImageUrl() {
        return info.getProfileImageUrl();
    }

    public int getFollowerCount() {
        return followers.getFollowCount();
    }

    public List<User> getFollowers() {
        return followers.getFollows();
    }

    public int getFollowingCount() {
        return followings.getFollowCount();
    }

    public List<User> getFollowings() {
        return followings.getFollows();
    }

    private void addFollower(User user) {
        this.followers.addFollow(user);
    }

    private void removeFollower(User user) {
        this.followers.removeFollow(user);
    }

    private void addFollowing(User user) {
        this.followings.addFollow(user);
    }

    private void removeFollowing(User user) {
        this.followings.removeFollow(user);
    }

    public void follow(User user) {
        if (user.equals(this)) {
            throw new IllegalArgumentException("자신을 팔로우할 수 없습니다.");
        }
        this.addFollowing(user);
        user.addFollower(this);
    }

    public void unfollow(User user) {
        if (user.equals(this)) {
            throw new IllegalArgumentException("자신을 언팔로우할 수 없습니다.");
        }
        this.removeFollowing(user);
        user.removeFollower(this);
    }

    public List<Post> getFeeds() {
        return feeds.getPosts();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
