package store.kirinit.communityfeed.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.post.domain.content.PostContent;
import store.kirinit.communityfeed.user.domain.User;
import store.kirinit.communityfeed.user.domain.UserInfo;

class PostTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, new PostContent("content"), user);

    @Test
    void 게시글_좋아요_테스트() throws Exception {
        // given
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void 자기글_좋아요_테스트() throws Exception {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () ->  post.like(user));
    }

    @Test
    void 게시글_좋아요_취소_테스트() throws Exception {
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void 게시글_좋아요_취소_실패_테스트() throws Exception {
        // given
        // when
        post.unlike();
        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void 게시글_컨텐츠_수정_테스트() {
        // given
        String updatedText = "this is a updated content";

        // when
        post.changePost(user, updatedText, PostState.PUBLIC);

        // then
        assertEquals(updatedText, post.getContent());
    }

    @Test
    void 게시글_다른유저_수정_테스트() {
        // given
        String updatedText = "this is a updated content";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> post.changePost(otherUser, updatedText, PostState.PUBLIC));
    }

}