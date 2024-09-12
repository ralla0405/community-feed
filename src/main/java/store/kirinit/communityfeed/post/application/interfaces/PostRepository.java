package store.kirinit.communityfeed.post.application.interfaces;

import store.kirinit.communityfeed.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
