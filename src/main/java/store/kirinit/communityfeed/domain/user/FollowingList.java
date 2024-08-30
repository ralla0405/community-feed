package store.kirinit.communityfeed.domain.user;

import java.util.List;

public class FollowingList {
    private final List<User> followings;

    public FollowingList() {
        this(List.of());
    }

    public FollowingList(List<User> followings) {
        this.followings = followings;
    }

    public int getFollowingCount() {
        return followings.size();
    }

    public List<User> getFollowings() {
        return followings;
    }

    public void addFollowing(User user) {
        if (followings.contains(user)) {
            throw new IllegalArgumentException("이미 팔로잉 중인 사용자입니다.");
        }
        followings.add(user);
    }

    public void removeFollowing(User user) {
        if (!followings.contains(user)) {
            throw new IllegalArgumentException("팔로잉 중인 사용자가 아닙니다.");
        }
        followings.remove(user);
    }
}
