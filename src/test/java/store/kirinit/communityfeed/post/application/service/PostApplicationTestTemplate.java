package store.kirinit.communityfeed.post.application.service;

import store.kirinit.communityfeed.fake.FakeObjectFactory;
import store.kirinit.communityfeed.post.application.CommentService;
import store.kirinit.communityfeed.post.application.PostService;
import store.kirinit.communityfeed.post.application.dto.CreateCommentRequestDto;
import store.kirinit.communityfeed.post.application.dto.CreatePostRequestDto;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.PostState;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.application.service.UserService;
import store.kirinit.communityfeed.user.domain.User;

class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();
    final User user = userService.createUser(new CreateUserRequestDto("user1", ""));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", ""));

    CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "content", PostState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String testCommentText = "This is a test comment";
    CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), testCommentText);

}
