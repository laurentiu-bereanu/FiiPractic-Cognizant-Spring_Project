package ro.iasi.fiipractic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.iasi.fiipractic.model.User;
import ro.iasi.fiipractic.service.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @GetMapping(value = "/search/{keyword}")
    public List<User> getAllUsersFilteredByKeyword(@PathVariable String keyword) {
        return userService.getAllUsersFilteredByKeyword(keyword);
    }

    @PostMapping(value = "{username}/follow")
    public void followUser(@PathVariable String username, @RequestParam String userFollowed) {
        userService.followUser(userFollowed, username);
    }

    @DeleteMapping(value = "{username}/unfollow")
    public void unfollowUser(@PathVariable String username, @RequestParam String userUnfollowed) {
        userService.unfollowUser(userUnfollowed, username);
    }

    @DeleteMapping(value = "/unregister/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unregisterUser(@PathVariable String username) {
        userService.unregisterUser(username);
    }
}

