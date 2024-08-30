package store.kirinit.communityfeed.domain.comment;

import java.time.LocalDateTime;
import store.kirinit.communityfeed.domain.like.Like;
import store.kirinit.communityfeed.domain.user.User;

public class Comment {
    private String content;
    private final User writer;
    private final Like like;
    private boolean isUpdated;
    private LocalDateTime updatedAt;

    public Comment(String content, User writer) {
        checkContent(content);
        this.content = content;
        this.writer = writer;
        this.like = new Like();
        this.isUpdated = false;
        this.updatedAt = LocalDateTime.now();
    }

    public String getContent() {
        return content;
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
        checkContent(content);
        this.content = content;
        this.isUpdated = true;
        this.updatedAt = LocalDateTime.now();
    }

    private void checkContent(String content) {
        if (content.length() > 100) {
            throw new IllegalArgumentException("댓글 내용은 100자 이하로 입력해주세요.");
        }
    }

}
