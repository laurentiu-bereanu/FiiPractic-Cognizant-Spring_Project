package ro.iasi.fiipractic.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.iasi.fiipractic.exception.user.UserNotFoundException;
import ro.iasi.fiipractic.exception.user.UsernameNotUniqueException;
import ro.iasi.fiipractic.model.User;
import ro.iasi.fiipractic.repository.mapper.UserRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USER", new UserRowMapper());
    }

    public int createUser(String username, String firstName, String lastName, String email, String password) {
        Integer numberOfUsersWithUsername = jdbcTemplate.queryForObject("SELECT COUNT(USERNAME) FROM USER WHERE USERNAME = ?", Integer.class, username);
        if(numberOfUsersWithUsername == 0) {
            return jdbcTemplate.update("INSERT INTO USER(USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, FOLLOWERS) VALUES (?, ?, ? ,?, ?, 0)", username, firstName, lastName, email, password);
        } else {
            throw new UsernameNotUniqueException(String.format("There is already a user with this username: %s", username));
        }
    }

    public int followUser(String userFollowed, String userWhoFollows) {
        return jdbcTemplate.update("INSERT INTO FOLLOWERS(USERNAME,FOLLOWING) VALUES(?,?)", userWhoFollows, userFollowed);
    }

    public List<User> getAllUsersFilteredByKeyword(String keyword) {
        return jdbcTemplate.query("SELECT * FROM USER WHERE USERNAME = ? OR FIRST_NAME = ? OR LAST_NAME = ?", new UserRowMapper(), keyword, keyword, keyword);
    }

    public List<Integer> unregisterUser(String username) {
        List<Integer> jdbcScripts = new ArrayList<>();
        jdbcScripts.add(jdbcTemplate.update("DELETE FROM USER WHERE USERNAME = ?", username));
        jdbcScripts.add(jdbcTemplate.update("DELETE FROM POST WHERE USER_ID = ?", username));
        jdbcScripts.add(jdbcTemplate.update("DELETE FROM LIKES WHERE USERNAME = ?", username));
        return jdbcScripts;
    }

    public int unfollowUser(String userUnfollowed, String userWhoUnfollows) {
        return jdbcTemplate.update("DELETE FROM FOLLOWERS WHERE USERNAME = ? AND FOLLOWING = ?", userWhoUnfollows, userUnfollowed);
    }

    public User getUserById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with id %s was not found", id));
        }
    }

}
