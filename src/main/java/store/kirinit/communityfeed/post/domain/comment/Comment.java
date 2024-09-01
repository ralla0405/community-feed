package store.kirinit.communityfeed.post.domain.comment;

import java.time.LocalDateTime;
import store.kirinit.communityfeed.common.domain.PositiveIntegerCounter;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.content.CommentContent;
import store.kirinit.communityfeed.post.domain.content.Content;
import store.kirinit.communityfeed.user.domain.User;

public class Comment {
    private final Long id;
    private final Post post;
    private final User writer;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Post post, User writer, String content) {
        return new Comment(null, post, writer, new CommentContent(content));
    }

    public Comment(Long id, Post post, User writer, CommentContent content) {
        if (post == null) {
            throw new IllegalArgumentException("게시글이 없습니다.");
        }
        if (writer == null) {
            throw new IllegalArgumentException("작성자가 없습니다.");
        }
        this.id = id;
        this.post = post;
        this.writer = writer;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content.getContentText();
    }

    public String getWriterUserName() {
        return writer.getUserName();
    }

    public String getWriterProfileImageUrl() {
        return writer.getProfileImageUrl();
    }

    public Post getPost() {
        return post;
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public boolean isUpdated() {
        return content.isEdited();
    }

    public LocalDateTime getUpdatedAt() {
        return content.getUpdatedDateTime();
    }

    public void like(User user) {
        if (writer.equals(user)) {
            throw new IllegalArgumentException("자기글에 좋아요할 수 없습니다.");
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void changeContent(User user, String content) {
        if (!writer.equals(user)) {
            throw new IllegalArgumentException("댓글 작성자만 수정할 수 있습니다.");
        }
        this.content.changeContent(content);
    }

}
