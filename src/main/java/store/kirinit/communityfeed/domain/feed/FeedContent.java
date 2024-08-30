package store.kirinit.communityfeed.domain.feed;

public class FeedContent {

    private String content;

    public FeedContent(String content) {
        checkContent(content);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void changeContent(String content) {
        checkContent(content);
        this.content = content;
    }

    private void checkContent(String content) {
        if (content.length() < 5 || content.length() > 500) {
            throw new IllegalArgumentException("피드 내용은 5자 이상 100자 이하로 입력해주세요.");
        }
    }

}
