package store.kirinit.communityfeed.user.application.service;

import org.springframework.stereotype.Service;
import store.kirinit.communityfeed.user.application.dto.CreateUserRequestDto;
import store.kirinit.communityfeed.user.application.dto.GetUserResponseDto;
import store.kirinit.communityfeed.user.application.interfaces.UserRepository;
import store.kirinit.communityfeed.user.domain.User;
import store.kirinit.communityfeed.user.domain.UserInfo;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto requestDto) {
        UserInfo userInfo = new UserInfo(requestDto.name(), requestDto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }
}
