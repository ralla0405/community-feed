package store.kirinit.communityfeed.post.repository;

import java.util.HashMap;
import java.util.Map;
import store.kirinit.communityfeed.post.application.interfaces.PostRepository;
import store.kirinit.communityfeed.post.domain.Post;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }
        long id = store.size() + 1;
        Post newPost = new Post(id, post.getContentObject(), post.getWriter());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
