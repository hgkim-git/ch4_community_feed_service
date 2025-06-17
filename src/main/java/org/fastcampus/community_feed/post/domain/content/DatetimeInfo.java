package org.fastcampus.community_feed.post.domain.content;

import java.time.LocalDateTime;

public class DatetimeInfo {
  private boolean isEdited;
  private LocalDateTime dateTime;

  public DatetimeInfo() {
    this.isEdited = false;
    this.dateTime = LocalDateTime.now();
  }
  // 작은 객체에서 큰객체로 테스트 하기 위해 Post를 테스트 하기전에 PostContent 안의 DateTimeInfo를 먼저 테스트 한다.
  public void updateEditDatetime() {
    this.isEdited = true;
    this.dateTime = LocalDateTime.now();
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public boolean isEdited() {
    return isEdited;
  }
}
