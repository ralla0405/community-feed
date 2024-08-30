package store.kirinit.communityfeed.domain.user;

import java.util.List;

public class FollowerList {

    private final List<User> followers;

    public FollowerList() {
        this(List.of());
    }

    public FollowerList(List<User> followers) {
        this.followers = followers;
    }

    public int getFollowerCount() {
        return followers.size();
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void addFollower(User user) {
        if (followers.contains(user)) {
            throw new IllegalArgumentException("이미 팔로워인 사용자입니다.");
        }
        followers.add(user);
    }

    public void removeFollower(User user) {
        if (!followers.contains(user)) {
            throw new IllegalArgumentException("팔로워인 사용자가 아닙니다.");
        }
        followers.remove(user);
    }
}
