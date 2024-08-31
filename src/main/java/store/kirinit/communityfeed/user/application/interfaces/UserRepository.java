package store.kirinit.communityfeed.user.application.interfaces;

import java.util.Optional;
import store.kirinit.communityfeed.user.domain.User;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
