package store.kirinit.communityfeed.post.application;

import store.kirinit.communityfeed.post.application.dto.CreatePostRequestDto;
import store.kirinit.communityfeed.post.application.dto.LikeRequestDto;
import store.kirinit.communityfeed.post.application.dto.UpdatePostRequestDto;
import store.kirinit.communityfeed.post.application.interfaces.LikeRepository;
import store.kirinit.communityfeed.post.application.interfaces.PostRepository;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.user.application.service.UserService;
import store.kirinit.communityfeed.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Post createPost(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = Post.createPost(null, user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        post.changePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }
        post.like(user);
        likeRepository.like(post, user);
        postRepository.save(post);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
            postRepository.save(post);
        }
    }
}
