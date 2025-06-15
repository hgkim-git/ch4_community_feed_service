package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;

// 비즈니스 로직을 처리하기 보다는 다른 객체와 협업하는 객체
public class UserService {

  /**
   * 저장 기능을 위해 Repository 레이어에 접근 필요
   * But Repository와 같은 저수준 컴포넌트는 비즈니스 로직을 처리하는 도메인이나 어플리케이션 레이어에서
   * 직접 의존하면 안된다고 지난 시간에 배움(클린 아키텍쳐 부분).
   * => 따라서 Repository에 접근할 수 있는 인터페이스를 만들어 의존성을 역전시키도록 함
   */

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(CreateUserRequestDto dto) {
    UserInfo userInfo = new UserInfo(dto.userName(), dto.userProfileUrl());
    User user = new User(null, userInfo);
    return userRepository.save(user);
  }

  public User getUser(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }
}
