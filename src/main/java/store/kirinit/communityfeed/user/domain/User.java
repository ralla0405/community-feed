package store.kirinit.communityfeed.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import store.kirinit.communityfeed.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private UserInfo info;
    private PositiveIntegerCounter followerCount;
    private PositiveIntegerCounter followingCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.info = userInfo;
        this.followerCount = new PositiveIntegerCounter();
        this.followingCount = new PositiveIntegerCounter();
    }

    public String getUserName() {
        return info.getName();
    }

    public String getProfileImageUrl() {
        return info.getProfileImageUrl();
    }

    public void follow(User followee) {
        if (this.equals(followee)) {
            throw new IllegalArgumentException("");
        }

        followingCount.increase();
        followee.increaseFollowerCount();
    }

    public void unfollow(User followee) {
        if (this.equals(followee)) {
            throw new IllegalArgumentException("");
        }

        followingCount.decrease();
        followee.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    public int getFollowingCount() {
        return followingCount.getCount();
    }

    public int getFollowerCount() {
        return followerCount.getCount();
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
