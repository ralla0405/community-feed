package store.kirinit.communityfeed.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import store.kirinit.communityfeed.user.application.interfaces.UserRepository;
import store.kirinit.communityfeed.user.domain.User;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;
import store.kirinit.communityfeed.user.repository.jpa.JpaUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return entity.toUser();
    }
}
