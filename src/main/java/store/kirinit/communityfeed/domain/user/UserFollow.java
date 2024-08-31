package store.kirinit.communityfeed.domain.user;

import java.util.ArrayList;
import java.util.List;

public class UserFollow {

    private final List<User> follows;

    public UserFollow() {
        this(new ArrayList<>());
    }

    public UserFollow(List<User> follows) {
        this.follows = follows;
    }

    public int getFollowCount() {
        return follows.size();
    }

    public List<User> getFollows() {
        return follows;
    }

    public void addFollow(User user) {
        if (follows.contains(user)) {
            throw new IllegalArgumentException("이미 추가된 사용자입니다.");
        }
        follows.add(user);
    }

    public void removeFollow(User user) {
        if (!follows.contains(user)) {
            throw new IllegalArgumentException("추가된 사용자가 아닙니다.");
        }
        follows.remove(user);
    }
}
