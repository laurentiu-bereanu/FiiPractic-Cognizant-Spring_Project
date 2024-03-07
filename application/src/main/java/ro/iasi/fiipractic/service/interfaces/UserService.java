package ro.iasi.fiipractic.service.interfaces;

import ro.iasi.fiipractic.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void registerUser(User user);

    List<User> getAllUsersFilteredByKeyword(String keyword);

    void followUser(String userFollowed, String userWhoFollows);

    void unregisterUser(String username);

    void unfollowUser(String userUnfollowed, String userWhoFollows);

}
