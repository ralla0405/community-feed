package store.kirinit.communityfeed.post.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import store.kirinit.communityfeed.common.domain.PositiveIntegerCounter;
import store.kirinit.communityfeed.post.domain.content.Content;
import store.kirinit.communityfeed.post.domain.content.PostContent;
import store.kirinit.communityfeed.user.domain.User;

@Builder
@Getter
@AllArgsConstructor
public class Post {
    private final Long id;
    private final User writer;
    private final Content content;
    private PostState state;
    private final PositiveIntegerCounter likeCount;

    public static Post createPost(Long id, User writer, String content, PostState state) {
        return new Post(id, new PostContent(content), writer, state);
    }

    public static Post createDefaultPost(Long id, User writer, String content) {
        return new Post(id, new PostContent(content), writer, PostState.PUBLIC);
    }

    public Post(Long id, Content content, User writer) {
        this(id, content, writer, PostState.PUBLIC);
    }

    public Post(Long id, Content content, User writer, PostState state) {
        if (writer == null) {
            throw new IllegalArgumentException("작성자가 없습니다.");
        }
        this.id = id;
        this.content = content;
        this.state = state;
        this.writer = writer;
        this.likeCount = new PositiveIntegerCounter();
    }

    public Content getContentObject() {
        return content;
    }

    public String getContent() {
        return content.getContentText();
    }

    public String getStateString() {
        return state.name();
    }

    public String getWriterUserName() {
        return writer.getUserName();
    }

    public String getWriterProfileImageUrl() {
        return writer.getProfileImageUrl();
    }

    public int getLikeCount() {
        return likeCount.getCount();
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

    public boolean isUpdated() {
        return content.isEdited();
    }

    public LocalDateTime getUpdatedAt() {
        return content.getUpdatedDateTime();
    }

    public void changePost(User user, String content, PostState state) {
        if (!this.writer.equals(user)) {
            throw new IllegalArgumentException("피드 작성자만 수정할 수 있습니다.");
        }
        this.state = state;
        this.content.changeContent(content);
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
