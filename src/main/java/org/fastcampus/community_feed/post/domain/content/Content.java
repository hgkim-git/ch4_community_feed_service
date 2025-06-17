package org.fastcampus.community_feed.post.domain.content;

public abstract class Content {
  protected String contentText;
  // 작은 객체에서 큰객체로 테스트 하기 위해 Post를 테스트 하기전에 PostContent 안의 DateTimeInfo를 먼저 테스트 한다.
  protected final DatetimeInfo datetimeInfo;

  Content(String content) {
    checkLength(content);
    this.contentText = content;
    this.datetimeInfo = new DatetimeInfo();
  }

  public void updateContent(String content) {
    checkLength(content);
    this.contentText = content;
    this.datetimeInfo.updateEditDatetime();
  }

  abstract void checkLength(String content);

  public String getContentText() {
    return contentText;
  }

  public boolean isEdited() {
    return datetimeInfo.isEdited();
  }
}
