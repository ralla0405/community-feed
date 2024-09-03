package store.kirinit.communityfeed.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {

    @Test
    void 업데이트할_경우() {
        // given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();

        // when
        datetimeInfo.changeDateTimeInfo();

        // then
        assertTrue(datetimeInfo.isEdited());
        assertEquals(localDateTime, datetimeInfo.getDateTime());
    }

}