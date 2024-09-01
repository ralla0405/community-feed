package store.kirinit.communityfeed.post.domain.content;

import java.time.LocalDateTime;
import store.kirinit.communityfeed.post.domain.common.DatetimeInfo;

// 다형성 활용
public abstract class Content {
    protected String contentText;
    protected final DatetimeInfo datetimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
       return contentText;
    }

    public boolean isEdited() {
        return datetimeInfo.isEdited();
    }

    public LocalDateTime getUpdatedDateTime() {
        return datetimeInfo.getDateTime();
    }

    public void changeContent(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo.changeDateTimeInfo();
    }
}
