package store.kirinit.communityfeed.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import store.kirinit.communityfeed.user.repository.entity.UserRelationEntity;
import store.kirinit.communityfeed.user.repository.entity.UserRelationIdEntity;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
