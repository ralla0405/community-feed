package store.kirinit.communityfeed.domain.comment;

import java.time.LocalDateTime;
import store.kirinit.communityfeed.domain.like.Like;
import store.kirinit.communityfeed.domain.post.Post;
import store.kirinit.communityfeed.domain.post.content.CommentContent;
import store.kirinit.communityfeed.domain.user.User;

public class Comment {
    private final Long id;
    private final CommentContent content;
    private final User writer;
    private final Post post;
    private final Like like;
    private boolean isUpdated;
    private LocalDateTime updatedAt;

    public Comment(Long id, Post post, User writer, CommentContent content) {
        if (post == null) {
            throw new IllegalArgumentException("게시글이 없습니다.");
        }
        if (writer == null) {
            throw new IllegalArgumentException("작성자가 없습니다.");
        }
        this.id = id;
        this.post = post;
        this.content = content;
        this.writer = writer;
        this.like = new Like();
        this.isUpdated = false;
        this.updatedAt = LocalDateTime.now();
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
        return like.getLikeCount();
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int incrementAndGetLikeCount(User user) {
        return like.incrementAndGetLikeCount(user);
    }

    public int decrementAndGetLikeCount(User user) {
        return like.decrementAndGetLikeCount(user);
    }

    public void changeContent(User user, String content) {
        if (!writer.equals(user)) {
            throw new IllegalArgumentException("댓글 작성자만 수정할 수 있습니다.");
        }
        this.content.changeContent(content);
        this.isUpdated = true;
        this.updatedAt = LocalDateTime.now();
    }

}
