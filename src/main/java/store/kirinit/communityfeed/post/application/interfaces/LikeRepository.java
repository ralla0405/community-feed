package store.kirinit.communityfeed.post.application.interfaces;

import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.comment.Comment;
import store.kirinit.communityfeed.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

    boolean checkLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unlike(Comment comment, User user);
}
