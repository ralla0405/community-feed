package store.kirinit.communityfeed.post.application.interfaces;

import store.kirinit.communityfeed.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long commentId);
}
