package store.kirinit.communityfeed.domain.post.content;


public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()){
            throw new IllegalArgumentException("피드 내용을 입력해주세요.");
        }

        if (contentText.length() < MIN_POST_LENGTH) {
            throw new IllegalArgumentException("피드 내용은 5자 이상으로 입력해주세요.");
        }
        if (contentText.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException("피드 내용은 100자 이하로 입력해주세요.");
        }
    }

}
