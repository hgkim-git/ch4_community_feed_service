package org.fastcampus.community_feed.user.application.dto;

// DTO 객체도 네이밍 규칙을 만들어서 생성하는 것이 좋음
// 여기서는 행동 + 변경 or 생성 대상 이름 + 요청/응답 + "Dto"
// 아래는 기존 Java에서임. 자바 14부터는 record라는 타입을 지원함
// record 타입으로 선언한 경우, getter 뿐만 아니라 toString, hashcode 도 자동으로 생성해줌
// IntelliJ에서는 IDE에서 제안해준다

/**
 *
public class CreateUserRequestDto {

  private final String name;
  private final String userProfileUrl;

  public CreateUserRequestDto(String name, String userProfileUrl) {
    this.name = name;
    this.userProfileUrl = userProfileUrl;
  }

  public String getName() {
    return name;
  }

  public String getUserProfileUrl() {
    return userProfileUrl;
  }
}
 */
// 자바 14부터 지원되는 record 타입

public record CreateUserRequestDto(String userName, String userProfileUrl) {

}
