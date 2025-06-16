package org.fastcampus.community_feed.common;

import org.fastcampus.community_feed.post.application.CommentService;
import org.fastcampus.community_feed.post.application.PostService;
import org.fastcampus.community_feed.post.application.interfaces.CommentRepository;
import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.application.interfaces.PostRepository;
import org.fastcampus.community_feed.post.repository.FakeCommentRepository;
import org.fastcampus.community_feed.post.repository.FakeLikeRepository;
import org.fastcampus.community_feed.post.repository.FakePostRepository;
import org.fastcampus.community_feed.user.application.UserRelationService;
import org.fastcampus.community_feed.user.application.UserService;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.repository.FakeUserRelationRepository;
import org.fastcampus.community_feed.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    /**
     * 이러한 페이크 테스트 객체를 만드는 것에는 어떤 이점이 있을까?
     * - 의문점
     *      - H2같은 인메모리 DB에 바로 하면 안됨?
     *      - 테스트 데이터 잘 못 되어있으면 어차피 테스트 결과가 잘못 될텐데
     *      - 애초에 쿼리가 잘 못되면 의미 없는거 아님?
     * =>
     *      - 물론 만약 금방 오픈하는 프로젝트면 의미가 없을 수 있음(이미 DB가 결정되어 있다면)
     *      - 다만 어떤 DBMS를 쓸지 결정되어있지 않은 상태에서 테스트 먼저 해야된다면
     *      - H2나 이런 것들로 하나 만들어서 이미 코드를 작성해 버리면 나중에 DB 결정되고 테스트 코드를 다 엎어야 함
     *      - 또 H2같은 테스트용 DB에 설정하는 것도 시간이 소요됨
     *      - 즉 페이크 객체는 유연하고, 빠르게 테스트가 가능함
     */
    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(fakeUserRelationRepository, userService);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
