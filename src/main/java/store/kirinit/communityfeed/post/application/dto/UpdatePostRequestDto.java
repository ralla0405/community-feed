package store.kirinit.communityfeed.post.application.dto;

import store.kirinit.communityfeed.post.domain.PostState;

public record UpdatePostRequestDto(Long userId, String content, PostState state) {

}
