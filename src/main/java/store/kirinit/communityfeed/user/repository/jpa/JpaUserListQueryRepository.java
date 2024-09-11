package store.kirinit.communityfeed.user.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.kirinit.communityfeed.user.application.dto.GetUserListResponseDto;
import store.kirinit.communityfeed.user.repository.entity.UserEntity;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new store.kirinit.communityfeed.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
        + "FROM UserRelationEntity ur "
        + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
        + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "SELECT new store.kirinit.communityfeed.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
        + "FROM UserRelationEntity ur "
        + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
        + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
