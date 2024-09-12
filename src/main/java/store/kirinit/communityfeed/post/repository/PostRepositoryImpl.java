package store.kirinit.communityfeed.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.post.application.interfaces.PostRepository;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;
import store.kirinit.communityfeed.post.repository.jpa.JpaPostRepository;
import store.kirinit.communityfeed.post.repository.post_queue.UserPostQueueCommandRepository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if (postEntity.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }
        postEntity = jpaPostRepository.save(postEntity);
        commandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
