package store.kirinit.communityfeed.user.application.interfaces;

import store.kirinit.communityfeed.user.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);
}
