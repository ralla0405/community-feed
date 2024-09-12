package store.kirinit.communityfeed.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.post.application.interfaces.LikeRepository;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.comment.Comment;
import store.kirinit.communityfeed.post.repository.entity.comment.CommentEntity;
import store.kirinit.communityfeed.post.repository.entity.like.LikeEntity;
import store.kirinit.communityfeed.post.repository.entity.post.PostEntity;
import store.kirinit.communityfeed.post.repository.jpa.JpaCommentRepository;
import store.kirinit.communityfeed.post.repository.jpa.JpaLikeRepository;
import store.kirinit.communityfeed.post.repository.jpa.JpaPostRepository;
import store.kirinit.communityfeed.user.domain.User;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
//        jpaLikeRepository.save(likeEntity); <- 불필요한 조회 쿼리를 줄이기 위해
        entityManager.persist(likeEntity);
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
//        jpaLikeRepository.save(likeEntity); <- 불필요한 조회 쿼리를 줄이기 위해
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }
}
