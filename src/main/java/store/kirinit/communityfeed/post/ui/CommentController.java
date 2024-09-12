package store.kirinit.communityfeed.post.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.kirinit.communityfeed.common.ui.Response;
import store.kirinit.communityfeed.post.application.CommentService;
import store.kirinit.communityfeed.post.application.dto.CreateCommentRequestDto;
import store.kirinit.communityfeed.post.application.dto.LikeRequestDto;
import store.kirinit.communityfeed.post.application.dto.UpdateCommentRequestDto;
import store.kirinit.communityfeed.post.domain.comment.Comment;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(
        @PathVariable(name = "commentId") Long commentId,
        @RequestBody UpdateCommentRequestDto dto
    ) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }
}
