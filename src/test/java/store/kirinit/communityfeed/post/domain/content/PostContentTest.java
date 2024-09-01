package store.kirinit.communityfeed.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void 컨텐츠_생성_테스트() throws Exception {
        // given
        String text = "this is a test content";
        // when
        PostContent postContent = new PostContent(text);
        // then
        assertEquals(text, postContent.getContentText());
    }

    @Test
    void 컨텐츠_길이_초과_테스트() throws Exception {
        // given
        String text = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슭"})
    void 컨텐츠_한글_테스트(String koreanWord) throws Exception {
        // given
        String text = koreanWord.repeat(501);
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void 컨텐츠_길이_미만_테스트() throws Exception {
        // given
        String text = "a".repeat(4);
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 컨텐츠_빈값_테스트(String text) throws Exception {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void 컨텐츠_수정_테스트() throws Exception {
        // given
        String text = "this is a test content";
        PostContent postContent = new PostContent(text);
        String updatedText = "this is a updated content";
        // when
        postContent.changeContent(updatedText);
        // then
        assertEquals(updatedText, postContent.getContentText());
    }

    @Test
    void 컨텐츠_수정_시_수정시간_테스트() throws Exception {
        // given
        String text = "this is a test content";
        PostContent postContent = new PostContent(text);
        // when
        postContent.changeContent("this is a updated content");
        // then
        assertTrue(postContent.isEdited());
    }

    @Test
    void 컨텐츠_수정_시_수정시간_갱신_테스트() throws Exception {
        // given
        String text = "this is a test content";
        PostContent postContent = new PostContent(text);
        // when
        postContent.changeContent("this is a updated content");
        // then
        assertEquals(postContent.getUpdatedDateTime(), postContent.getUpdatedDateTime());
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슭"})
    void 컨텐츠_수정_한글_테스트(String koreanWord) throws Exception {
        // given
        String text = "this is a test content";
        PostContent postContent = new PostContent(text);
        String updatedText = koreanWord.repeat(501);
        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent.changeContent(updatedText));
    }

}