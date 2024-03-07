package ro.iasi.fiipractic.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.iasi.fiipractic.model.Reply;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyRowMapper implements RowMapper<Reply> {

    @Override
    public Reply mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Reply reply = new Reply();

        reply.setReply(rs.getString("MESSAGE"));
        reply.setAuthor(rs.getString("USERNAME"));
        reply.setPrivacy(rs.getBoolean("PRIVACY"));

        return reply;
    }
}