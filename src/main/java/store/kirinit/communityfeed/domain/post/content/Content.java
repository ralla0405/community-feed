package store.kirinit.communityfeed.domain.post.content;

// 다형성 활용
public abstract class Content {
    String contentText;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
       return contentText;
    }

    public void changeContent(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
    }
}
