package ro.iasi.fiipractic.service;

import org.springframework.stereotype.Service;
import ro.iasi.fiipractic.model.Post;
import ro.iasi.fiipractic.model.Reply;
import ro.iasi.fiipractic.repository.PostDAO;
import ro.iasi.fiipractic.service.interfaces.PostService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostDAO postRepository;

    public PostServiceImpl(PostDAO postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

    @Override
    public List<Post> getAllPostsForUsername(String username) {
        return postRepository.getAllPostsForUsername(username);
    }

    @Override
    public List<Post> getAllPostsForUsername(String username, Timestamp timestamp) {
        return postRepository.getAllPostsForUsername(username, timestamp);
    }

    @Override
    public List<Post> getAllPostsWithMentions(String username) {
        return postRepository.getAllPostsWithMentions(username);
    }

    @Override
    public List<Post> getCurrentFeed(String username) {
        return postRepository.getCurrentFeed(username);
    }

    @Override
    public void addPost(Post post) {
        postRepository.createPost(post.getAuthor(), post.getPostMessage());
    }

    @Override
    public void deletePost(int postId) {
        postRepository.deletePost(postId);
    }

    @Override
    public void likePost(int postId) {
        postRepository.likePost(postId);
    }

    @Override
    public void unlikePost( int postId) {
        postRepository.unlikePost(postId);
    }

    @Override
    public void addReply(Reply reply, String postId) {
        postRepository.addPost(reply, postId);
    }
}
