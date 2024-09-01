package store.kirinit.communityfeed.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void 댓글_생성_테스트() throws Exception {
        // given
        String text = "this is a test content";
        // when
        CommentContent commentContent = new CommentContent(text);
        // then
        assertEquals(text, commentContent.getContentText());
    }

    @Test
    void 댓글_길이_초과_테스트() throws Exception {
        // given
        String text = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슭"})
    void 댓글_한글_테스트(String koreanWord) throws Exception {
        // given
        String text = koreanWord.repeat(101);
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

}