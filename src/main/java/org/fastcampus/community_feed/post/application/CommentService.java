package org.fastcampus.community_feed.post.application;

import org.fastcampus.community_feed.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.community_feed.post.application.dto.LikeRequestDto;
import org.fastcampus.community_feed.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.community_feed.post.application.interfaces.CommentRepository;
import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.comment.Comment;
import org.fastcampus.community_feed.user.application.UserService;
import org.fastcampus.community_feed.user.domain.User;

public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    /**
     * 점점 서비스에서 의존하고 있는 객체가 많아짐
     *  -> 테스트 할 때마다 의존성을 주입해야되서 테스트 하기 번거로움
     *  -> 의존성이 변경되게 되면 테스트를 위해 작성했던 코드들을 다 변경해야 함
     *  ==> 필요한 의존성을 주입해서 싱글톤인 페이크 객체를 리턴해주는 팩토리 클래스(FakeObjectFactory)를 통해 테스트 진행
      */
    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User author = userService.getUser(dto.authorId());
        Comment comment = new Comment(null, post, author, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User author = userService.getUser(dto.authorId());
        comment.updateContent(author, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.id());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.id());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }
}
