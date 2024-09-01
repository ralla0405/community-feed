package store.kirinit.communityfeed.fake;

import store.kirinit.communityfeed.post.application.CommentService;
import store.kirinit.communityfeed.post.application.PostService;
import store.kirinit.communityfeed.post.application.interfaces.CommentRepository;
import store.kirinit.communityfeed.post.application.interfaces.LikeRepository;
import store.kirinit.communityfeed.post.application.interfaces.PostRepository;
import store.kirinit.communityfeed.post.application.repository.FakeCommentRepository;
import store.kirinit.communityfeed.post.application.repository.FakeLikeRepository;
import store.kirinit.communityfeed.post.application.repository.FakePostRepository;
import store.kirinit.communityfeed.user.application.interfaces.UserRelationRepository;
import store.kirinit.communityfeed.user.application.interfaces.UserRepository;
import store.kirinit.communityfeed.user.application.repository.FakeUserRelationRepository;
import store.kirinit.communityfeed.user.application.repository.FakeUserRepository;
import store.kirinit.communityfeed.user.application.service.UserRelationService;
import store.kirinit.communityfeed.user.application.service.UserService;

public class FakeObjectFactory {
    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(fakeCommentRepository, userService, postService, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
