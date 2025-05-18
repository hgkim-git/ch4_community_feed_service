package org.fastcampus.community_feed.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserInfoTest {

  @Test
  void givenNameAndProfileImage_whenCreated_thenThrowNothing() {
    // given
    String name = "John";
    String profileImageUrl = "www.naver.com";

    //when
    //then
    assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl)
    );
  }

  @Test
  void givenEmptyNameAndProfileImageWhenCreatedThenThrowException() {
    Assertions.assertThrows(Exception.class,() -> new UserInfo("", ""));
  }

}
