package ro.iasi.fiipractic.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.iasi.fiipractic.model.Post;
import ro.iasi.fiipractic.model.Reply;
import ro.iasi.fiipractic.repository.mapper.PostRowMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDAO {

    private final JdbcTemplate jdbcTemplate;

    public PostDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> getAllPosts() {
        return jdbcTemplate.query("SELECT * FROM POST ORDER BY DATE_ADDED DESC", new PostRowMapper());
    }

    public List<Post> getAllPostsForUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM POST WHERE USER_ID = ? ORDER BY DATE_ADDED DESC", new PostRowMapper(), username);
    }

    public List<Post> getAllPostsForUsername(String username, Timestamp timestamp) {
        return jdbcTemplate.query("SELECT * FROM POST WHERE USER_ID = ? AND DATE_ADDED > ? ", new PostRowMapper(), username, timestamp);
    }

    public List<Post> getAllPostsWithMentions(String username) {
        String usernameInTemplate = "%@" + username + "%";
        return jdbcTemplate.query("SELECT * FROM POST WHERE MESSAGE LIKE ?", new PostRowMapper(), usernameInTemplate);
    }

    public List<Post> getCurrentFeed(String username) {
        return jdbcTemplate.query("SELECT * FROM POST ORDER BY DATE_ADDED DESC", new PostRowMapper());
    }

    public int createPost(String author, String message) {
        return jdbcTemplate.update("INSERT INTO POST(USER_ID, MESSAGE, LIKES, DATE_ADDED) VALUES (?, ? , 0, CURRENT_TIMESTAMP)", author, message);
    }

    public List<Integer> deletePost(int postId) {
        List<Integer> jdbcScripts = new ArrayList<>();
        jdbcScripts.add(jdbcTemplate.update("DELETE FROM POST WHERE ID = ?", postId));
        jdbcScripts.add(jdbcTemplate.update(("DELETE FROM LIKES WHERE POST_ID = ?"), postId));
        return jdbcScripts;
    }

    public int likePost(int postId) {
        return jdbcTemplate.update("UPDATE POST SET LIKES = LIKES + 1 WHERE ID = ?", postId);
    }

    public int unlikePost( int postId) {
        return jdbcTemplate.update("UPDATE POST SET LIKES = LIKES - 1 WHERE ID = ?", postId);
    }

    public int addPost(Reply reply, String postId) {
        return jdbcTemplate.update("INSERT INTO REPLY(MESSAGE, POST_ID, USERNAME, PRIVACY) VALUES(?, ?, ?, ?)", reply.getReply(), postId, reply.getAuthor(), reply.isPrivacy());
    }
}
