package store.kirinit.communityfeed.user.application.interfaces;

import store.kirinit.communityfeed.user.domain.User;

public interface UserRepository {

    User save(User user);

    User findById(Long id);
}
