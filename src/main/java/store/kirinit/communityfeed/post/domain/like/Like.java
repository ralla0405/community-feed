package store.kirinit.communityfeed.post.domain.like;

import java.util.ArrayList;
import java.util.List;
import store.kirinit.communityfeed.user.domain.User;

public class Like {

    private final List<User> likes;

    public Like() {
        this(new ArrayList<>());
    }

    public Like(List<User> users) {
        this.likes = users;
    }

    public List<User> getLikes() {
        return likes;
    }

    public int getLikeCount() {
        return likes.size();
    }

    public int incrementAndGetLikeCount(User user) {
        if (likes.contains(user)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 사용자입니다.");
        }
        likes.add(user);
        return getLikeCount();
    }

    public int decrementAndGetLikeCount(User user) {
        if (!likes.contains(user)) {
            throw new IllegalArgumentException("좋아요를 누르지 않은 사용자입니다.");
        }
        likes.remove(user);
        return getLikeCount();
    }

}
