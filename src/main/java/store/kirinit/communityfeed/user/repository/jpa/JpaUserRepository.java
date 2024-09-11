package store.kirinit.communityfeed.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
