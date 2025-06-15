package org.fastcampus.community_feed.user.application.interfaces;

import org.fastcampus.community_feed.user.domain.User;

// UserRepository에서 팔로우, 팔로잉 관련 기능 분리
// 나중에 이쪽 기능만 다른 DB로 분산되게 되면 수정이 필요하게 되므로 미리 분리해 놓는다.
public interface UserRelationRepository {

    // 파라미터에 유저 객체 전체를 넘기는 것에 장점
    // - 언팔하거나 팔로우 했을때 User 객체 안에 카운트 멤버 있음 팔로잉, 팔로워 카운트도 변경이 되어야 함
    // - 이처럼 하나의 트랜잭션 안에서 일어나야하는 것들은 하나의 메서드로 처리하면 유지보수가 쉬워지기 때문
    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    // 언팔할때 삭제
    void delete(User user, User targetUser);
}
