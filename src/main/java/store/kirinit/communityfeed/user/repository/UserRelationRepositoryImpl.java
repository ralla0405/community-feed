package store.kirinit.communityfeed.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import store.kirinit.communityfeed.post.repository.post_queue.UserPostQueueCommandRepository;
import store.kirinit.communityfeed.user.application.interfaces.UserRelationRepository;
import store.kirinit.communityfeed.user.domain.User;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;
import store.kirinit.communityfeed.user.repository.entity.UserRelationEntity;
import store.kirinit.communityfeed.user.repository.entity.UserRelationIdEntity;
import store.kirinit.communityfeed.user.repository.jpa.JpaUserRelationRepository;
import store.kirinit.communityfeed.user.repository.jpa.JpaUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
