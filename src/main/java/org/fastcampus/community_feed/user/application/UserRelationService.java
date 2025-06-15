package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.fastcampus.community_feed.user.domain.User;

// User에서 팔로우, 언팔 기능 분리
// 이처럼 작게 인터페이스나 클래스를 만들어야 테스트가 용이함.
public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;

    public UserRelationService(UserRelationRepository userRelationRepository, UserService userService) {
        this.userRelationRepository = userRelationRepository;
        this.userService = userService;
    }

    public void followUser(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("Already followed");
        }

        user.follow(targetUser);

        userRelationRepository.save(user, targetUser);
    }

    public void unfollowUser(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("Not followed yet");
        }

        user.unfollow(targetUser);

        userRelationRepository.delete(user, targetUser);
    }
}
