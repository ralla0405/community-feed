package store.kirinit.communityfeed.post.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.post.application.dto.LikeRequestDto;
import store.kirinit.communityfeed.post.application.dto.UpdateCommentRequestDto;
import store.kirinit.communityfeed.post.domain.comment.Comment;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void 댓글_저장_테스트() {
        // given
        // when
        Comment savedComment = commentService.createComment(commentRequestDto);
        // then
        String content = savedComment.getContent();
        assertEquals(testCommentText, content);
    }

    @Test
    void 댓글_수정_테스트() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        // when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(savedComment.getId(), user.getId(), "update content");
        String updateContent = "update content";
        commentService.updateComment(updateCommentRequestDto);
        // then
        Comment findComment = commentService.getComment(savedComment.getId());
        assertEquals(updateContent, findComment.getContent());
    }

    @Test
    void 댓글_좋아요_테스트() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        // when
        LikeRequestDto likeDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);
        // then
        Comment findComment = commentService.getComment(savedComment.getId());
        assertEquals(1, findComment.getLikeCount());
    }

    @Test
    void 댓글_좋아요_취소_테스트() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        // when
        LikeRequestDto likeDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);
        commentService.unlikeComment(likeDto);
        // then
        Comment findComment = commentService.getComment(savedComment.getId());
        assertEquals(0, findComment.getLikeCount());
    }

    @Test
    void 댓글_좋아요_중복_테스트() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        // when
        LikeRequestDto likeDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);
        commentService.likeComment(likeDto);
        // then
        Comment findComment = commentService.getComment(savedComment.getId());
        assertEquals(1, findComment.getLikeCount());
    }

    @Test
    void 댓글_좋아요_취소_중복_테스트() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        // when
        LikeRequestDto likeDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);
        commentService.unlikeComment(likeDto);
        commentService.unlikeComment(likeDto);
        // then
        Comment findComment = commentService.getComment(savedComment.getId());
        assertEquals(0, findComment.getLikeCount());
    }

    @Test
    void 댓글_좋아요_취소만_테스트() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        // when
        LikeRequestDto likeDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.unlikeComment(likeDto);
        // then
        Comment findComment = commentService.getComment(savedComment.getId());
        assertEquals(0, findComment.getLikeCount());
    }
}
