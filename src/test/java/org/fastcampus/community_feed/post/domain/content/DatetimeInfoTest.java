package org.fastcampus.community_feed.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
// 작은 객체에서 큰객체로 테스트 하기 위해 Post를 테스트 하기전에 PostContent 안의 DateTimeInfo를 먼저 테스트 한다.
class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DatetimeInfo info = new DatetimeInfo();
        LocalDateTime datetime = info.getDateTime();

        // when
        info.updateEditDatetime();

        // then
        assertNotEquals(datetime, info.getDateTime());
        assertTrue(info.isEdited());
    }

}
