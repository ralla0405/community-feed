package store.kirinit.communityfeed.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.post.application.interfaces.CommentRepository;
import store.kirinit.communityfeed.post.domain.Post;
import store.kirinit.communityfeed.post.domain.comment.Comment;
import store.kirinit.communityfeed.post.repository.entity.comment.CommentEntity;
import store.kirinit.communityfeed.post.repository.jpa.JpaCommentRepository;
import store.kirinit.communityfeed.post.repository.jpa.JpaPostRepository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);
        if (commentEntity.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return comment;
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long commentId) {
        CommentEntity commentEntity = jpaCommentRepository.findById(commentId).orElseThrow();
        return commentEntity.toComment();
    }
}
