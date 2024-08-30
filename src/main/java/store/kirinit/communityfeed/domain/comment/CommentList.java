package store.kirinit.communityfeed.domain.comment;

import java.util.List;

public class CommentList {
    private final List<Comment> comments;

    public CommentList() {
        this(List.of());
    }

    public CommentList(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getCommentCount() {
        return comments.size();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
