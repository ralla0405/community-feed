package store.kirinit.communityfeed.post.application;

import org.springframework.stereotype.Service;
import store.kirinit.communityfeed.post.application.dto.CreateCommentRequestDto;
import store.kirinit.communityfeed.post.application.dto.LikeRequestDto;
import store.kirinit.communityfeed.post.application.dto.UpdateCommentRequestDto;
import store.kirinit.communityfeed.post.application.interfaces.CommentRepository;
import store.kirinit.communityfeed.post.application.interfaces.LikeRepository;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.comment.Comment;
import store.kirinit.communityfeed.user.application.service.UserService;
import store.kirinit.communityfeed.user.domain.User;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final LikeRepository likeRepository;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());
        comment.changeContent(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }
        comment.like(user);
        likeRepository.like(comment, user);
        commentRepository.save(comment);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
            commentRepository.save(comment);
        }
    }

}
