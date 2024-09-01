package store.kirinit.communityfeed.post.application.interfaces;

import java.util.Optional;
import store.kirinit.communityfeed.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
