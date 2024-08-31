package store.kirinit.communityfeed.domain.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentList {
    private final List<Comment> comments;

    public CommentList() {
        this(new ArrayList<>());
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
