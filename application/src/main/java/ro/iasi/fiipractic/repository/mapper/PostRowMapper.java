package ro.iasi.fiipractic.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.iasi.fiipractic.model.Post;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Post post = new Post();

        post.setPostId(rs.getInt("ID"));
        post.setAuthor(rs.getString("USER_ID"));
        post.setPostMessage(rs.getString("MESSAGE"));
        post.setNumberOfLikes(rs.getInt("LIKES"));
        post.setDateAdded(rs.getTimestamp("DATE_ADDED"));

        return post;
    }

}