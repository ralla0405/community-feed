package store.kirinit.communityfeed.post.domain.comment;

import java.time.LocalDateTime;
import store.kirinit.communityfeed.post.domain.like.Like;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.content.CommentContent;
import store.kirinit.communityfeed.user.domain.User;

public class Comment {
    private final Long id;
    private final CommentContent content;
    private final User writer;
    private final Post post;
    private final Like like;

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
        return content.isEdited();
    }

    public LocalDateTime getUpdatedAt() {
        return content.getUpdatedDateTime();
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
    }

}
