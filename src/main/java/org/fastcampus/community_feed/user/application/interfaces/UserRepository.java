package org.fastcampus.community_feed.user.application.interfaces;

import java.util.Optional;
import org.fastcampus.community_feed.user.domain.User;

// UserService(고수준 컴포넌트)에서 Repository 기능 관련 저수준 컴포넌트를 사용하기 위한 인터페이스
// 클린 아키텍쳐를 적용하기 위해선 이러한 저수준 컴포넌트를 참조할때 인터페이스를 통해 참조하여,
// 저수준 컴포넌트를 직접 의존하지 못하게 하고 비즈니스 로직 외의 변경에 유연하게 한다
public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
