package store.kirinit.communityfeed.post.application.dto;

import store.kirinit.communityfeed.post.domain.PostState;

public record CreatePostRequestDto(Long userId, String content, PostState state) {

}
