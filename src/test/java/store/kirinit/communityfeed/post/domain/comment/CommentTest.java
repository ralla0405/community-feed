package store.kirinit.communityfeed.post.domain.comment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.content.CommentContent;
import store.kirinit.communityfeed.post.domain.content.PostContent;
import store.kirinit.communityfeed.user.domain.User;
import store.kirinit.communityfeed.user.domain.UserInfo;

class CommentTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, new PostContent("content"), user);
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    @Test
    void 댓글_좋아요_테스트() throws Exception {
        // given
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void 자기글_좋아요_테스트() throws Exception {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () ->  comment.like(user));
    }


    @Test
    void 댓글_좋아요_좋아요_취소_테스트() throws Exception {
        // given
        comment.like(otherUser);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void 댓글_좋아요_취소_테스트() throws Exception {
        // given
        // when
        comment.unlike();
        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void 댓글_컨텐츠_수정_테스트() {
        // given
        String updatedText = "this is a updated content";

        // when
        comment.changeContent(user, updatedText);

        // then
        assertEquals(updatedText, comment.getContent());
    }

    @Test
    void 댓글_다른유저_수정_테스트() {
        // given
        String updatedText = "this is a updated content";

        // when
        // then
        assertThrows(IllegalArgumentException.class,
            () -> comment.changeContent(otherUser, updatedText));
    }

    @Test
    void 댓글_글자수_초과_테스트() {
        // given
        String updatedText = "a".repeat(101);

        // when
        // then
        assertThrows(IllegalArgumentException.class,
            () -> comment.changeContent(user, updatedText));
    }

}