package store.kirinit.communityfeed.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {

}
