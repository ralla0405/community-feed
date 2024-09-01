package store.kirinit.communityfeed.post.application.interfaces;

import java.util.Optional;
import store.kirinit.communityfeed.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long commentId);
}
