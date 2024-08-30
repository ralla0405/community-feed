package store.kirinit.communityfeed.domain.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import store.kirinit.communityfeed.domain.comment.Comment;
import store.kirinit.communityfeed.domain.comment.CommentList;
import store.kirinit.communityfeed.domain.like.Like;
import store.kirinit.communityfeed.domain.post.content.PostContent;
import store.kirinit.communityfeed.domain.user.User;

public class Post {
    private final Long id;
    private final PostContent content;
    // 공개 대상(모두 공개, 팔로워 전용)을 설정
    private final boolean isPublic;
    private final User writer;
    private final Like like;
    private final CommentList comments;
    private boolean isUpdated;
    private LocalDateTime updatedAt;

    public Post(Long id, PostContent content, boolean isPublic, User writer) {
        if (writer == null) {
            throw new IllegalArgumentException("작성자가 없습니다.");
        }
        this.id = id;
        this.content = content;
        this.isPublic = isPublic;
        this.writer = writer;
        this.like = new Like();
        this.comments = new CommentList();
        this.isUpdated = false;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content.getContentText();
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getWriterUserName() {
        return writer.getUserName();
    }

    public String getWriterProfileImageUrl() {
        return writer.getProfileImageUrl();
    }

    public int getLikeCount() {
        return like.getLikeCount();
    }

    public int incrementAndGetLikeCount(User user) {
        if (writer.equals(user)) {
            throw new IllegalArgumentException("자신의 피드에는 좋아요를 누를 수 없습니다.");
        }
        return like.incrementAndGetLikeCount(user);
    }

    public int decrementAndGetLikeCount(User user) {
        return like.decrementAndGetLikeCount(user);
    }

    public List<User> getLikes() {
        return like.getLikes();
    }

    public int getCommentCount() {
        return comments.getCommentCount();
    }

    public List<Comment> getComments() {
        return comments.getComments();
    }

    public void addComment(Comment comment) {
        comments.addComment(comment);
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changeContent(User user, String content) {
        if (!writer.equals(user)) {
            throw new IllegalArgumentException("피드 작성자만 수정할 수 있습니다.");
        }
        this.content.changeContent(content);
        this.isUpdated = true;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
