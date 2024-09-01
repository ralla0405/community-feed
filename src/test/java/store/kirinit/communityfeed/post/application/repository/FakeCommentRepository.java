package store.kirinit.communityfeed.post.application.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import store.kirinit.communityfeed.post.application.interfaces.CommentRepository;
import store.kirinit.communityfeed.post.domain.comment.Comment;

public class FakeCommentRepository implements CommentRepository {
    private final Map<Long, Comment> store = new HashMap<>();


    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            store.put(comment.getId(), comment);
            return comment;
        }
        long id = store.size() + 1;
        Comment newComment = new Comment(id, comment.getPost(), comment.getWriter(), comment.getContentObject());
        store.put(id, newComment);
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(store.get(commentId));
    }
}
