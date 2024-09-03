package store.kirinit.communityfeed.post.repository.entity.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.kirinit.communityfeed.common.domain.PositiveIntegerCounter;
import store.kirinit.communityfeed.common.repository.entity.TimeBaseEntity;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.PostState;
import store.kirinit.communityfeed.post.domain.content.PostContent;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK를 사용하지 않는 이유는 writer가 삭제되어도 post가 삭제되지 않기 때문
    @ManyToOne
    @JoinColumn(name = "writer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity writer;

    private String content;

    @Convert(converter = PostStateConverter.class)
    private PostState state;

    private Integer likeCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.writer = new UserEntity(post.getWriter());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost() {
        return Post.builder()
            .id(id)
            .writer(writer.toUser())
            .content(new PostContent(content))
            .state(state)
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }
}
