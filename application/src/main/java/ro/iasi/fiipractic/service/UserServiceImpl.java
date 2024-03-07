package ro.iasi.fiipractic.service;

import org.springframework.stereotype.Service;
import ro.iasi.fiipractic.model.User;
import ro.iasi.fiipractic.repository.UserDAO;
import ro.iasi.fiipractic.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userRepository;

    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void registerUser(User user) {
        userRepository.createUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    @Override
    public List<User> getAllUsersFilteredByKeyword(String keyword) {
        return userRepository.getAllUsersFilteredByKeyword(keyword);
    }

    @Override
    public void followUser(String userFollowed, String userWhoFollows) {
        userRepository.followUser(userFollowed, userWhoFollows);
    }

    @Override
    public void unregisterUser(String username) {
        userRepository.unregisterUser(username);
    }

    @Override
    public void unfollowUser(String userUnfollowed, String userWhoUnfollows) {
        userRepository.unfollowUser(userUnfollowed, userWhoUnfollows);
    }
}
