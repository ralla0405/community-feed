package store.kirinit.communityfeed.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_COMMENT_LENGTH = 100;

    protected CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()){
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        if (contentText.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException("댓글 내용은 100자 이하로 입력해주세요.");
        }
    }
}
