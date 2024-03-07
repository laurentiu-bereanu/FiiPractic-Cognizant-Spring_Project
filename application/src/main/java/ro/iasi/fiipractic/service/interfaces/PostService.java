package ro.iasi.fiipractic.service.interfaces;

import ro.iasi.fiipractic.model.Post;
import ro.iasi.fiipractic.model.Reply;

import java.sql.Timestamp;
import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    List<Post> getAllPostsForUsername(String username);

    List<Post> getAllPostsForUsername(String username, Timestamp timestamp);

    List<Post> getAllPostsWithMentions(String username);

    List<Post> getCurrentFeed(String username);

    void addPost(Post post);

    void deletePost(int postId);

    void likePost(int postId);

    void unlikePost(int postId);

    void addReply(Reply reply, String postId);
}
