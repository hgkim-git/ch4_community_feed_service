package org.fastcampus.community_feed.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {

  private final UserInfo userInfo = new UserInfo("test", "");
  private final User user1 = new User(1L, userInfo);
  private final User user2 = new User(2L, userInfo);

  @Test
  void givenTwoUserWhenEqualThenReturnFalse() {
    // given : 위에 클래스 멤버로 생성된 user1, user2
    // when
    boolean value = user1.equals(user2);
    //then
    assertFalse(value);
  }

  @Test
  void givenSameUserWhenEqualThenReturnTrue() {
    // given
    User same = new User(1L, userInfo);
    // when
    boolean value = user1.equals(same);
    //then
    assertTrue(value);
  }


  @Test
  void givenCreateSameIdUserWhenEqualSameIdThenReturnTrue() {
    // given
    UserInfo testInfo = new UserInfo("test1", "1");
    User oneUser = new User(1L, testInfo);

    // when, then
    assertEquals(oneUser, user1);
  }

  @Test
  void givenTwoUserWhenCompareHashcodeThenReturnFalse() {
    // given
    int hash1 = user1.hashCode();
    int hash2 = user2.hashCode();
    // when
    boolean isSame = hash1 == hash2;
    // then
    assertFalse(isSame);
  }

  @Test
  void givenUser1WhenFollowUser2ThenUser1IncreaseFollowingCountUser2IncreaseFollowerCount() {
    // given, when
    user1.follow(user2);

    // then
    assertEquals(1, user1.getFollowingCount());
    assertEquals(0, user1.getFollowerCount());
    assertEquals(0, user2.getFollowingCount());
    assertEquals(1, user2.getFollowerCount());
  }

  @Test
  void givenUser1FollowUser2WhenUser1UnfollowUser2ThenReturnZero() {
    // given
    user1.follow(user2);

    //when
    user1.unfollow(user2);

    // then
    assertEquals(0, user1.getFollowingCount());
    assertEquals(0, user1.getFollowerCount());
    assertEquals(0, user2.getFollowingCount());
    assertEquals(0, user2.getFollowerCount());
  }

  @Test
  void whenUser1UnfollowUser2ThenAllCountZero() {
    //when
    user1.unfollow(user2);

    // then
    assertEquals(0, user1.getFollowingCount());
    assertEquals(0, user1.getFollowerCount());
    assertEquals(0, user2.getFollowingCount());
    assertEquals(0, user2.getFollowerCount());
  }


  @Test
  void givenOneUserWhenFollowSameUserThenThrowError() {
    assertThrows(IllegalArgumentException.class, () -> user1.follow(user1));
  }

  @Test
  void givenOneUserWhenUnfollowSameUserThenThrowError() {
    assertThrows(IllegalArgumentException.class, () -> user1.unfollow(user1));
  }
}
