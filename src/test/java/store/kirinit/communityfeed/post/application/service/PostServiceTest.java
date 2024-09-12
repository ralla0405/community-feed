package store.kirinit.communityfeed.post.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import store.kirinit.communityfeed.post.application.dto.LikeRequestDto;
import store.kirinit.communityfeed.post.application.dto.UpdatePostRequestDto;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.PostState;

class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    void 게시글_저장_테스트() {
        // given
        // when
        Post savedPost = postService.createPost(postRequestDto);
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals(findPost.getId(), savedPost.getId());
    }

    @Test
    void 게시글_수정_테스트() {
        // given
        Post savedPost = postService.createPost(postRequestDto);
        // when
        postService.updatePost(savedPost.getId(), new UpdatePostRequestDto(user.getId(), "update content", PostState.PRIVATE));
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals("update content", findPost.getContent());
    }

    @Test
    void 게시글_좋아요_테스트() {
        // given
        // when
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals(1, findPost.getLikeCount());
    }

    @Test
    void 게시글_좋아요_취소_테스트() {
        // given
        // when
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        postService.unlikePost(likeDto);
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals(0, findPost.getLikeCount());
    }

    @Test
    void 게시글_좋아요_중복_테스트() {
        // given
        // when
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        postService.likePost(likeDto);
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals(1, findPost.getLikeCount());
    }

    @Test
    void 게시글_좋아요_취소_중복_테스트() {
        // given
        // when
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        postService.unlikePost(likeDto);
        postService.unlikePost(likeDto);
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals(0, findPost.getLikeCount());
    }

    @Test
    void 게시글_좋아요_취소만_테스트() {
        // given
        // when
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeDto);
        // then
        Post findPost = postService.getPost(savedPost.getId());
        assertEquals(0, findPost.getLikeCount());
    }
}
