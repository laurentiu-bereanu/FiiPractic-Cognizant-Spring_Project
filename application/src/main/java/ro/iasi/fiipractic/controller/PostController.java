package ro.iasi.fiipractic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ro.iasi.fiipractic.model.Post;
import ro.iasi.fiipractic.model.Reply;
import ro.iasi.fiipractic.service.interfaces.PostService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(value = "/{username}")
    public List<Post> getAllPostsForUsername(@PathVariable String username, @RequestParam(required = false) Timestamp timestamp) {
        if (timestamp == null) {
            return postService.getAllPostsForUsername(username);
        } else {
            return postService.getAllPostsForUsername(username, timestamp);
        }
    }

    @GetMapping(value="/{username}/feed")
    public List<Post> getCurrentFeed(@PathVariable String username) {
        return postService.getCurrentFeed(username);
    }

    @GetMapping(value = "/mentions/{username}")
    public List<Post> getAllPostsWithMentions(@PathVariable String username) {
        return postService.getAllPostsWithMentions(username);
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }

    @DeleteMapping(value = "/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }

    @PatchMapping(value = "/{postId}/like")
    public void likePost(@PathVariable Integer postId) {
        postService.likePost(postId);
    }

    @PatchMapping(value = "/{postId}/unlike")
    public void unlikePost(@PathVariable Integer postId) {
        postService.unlikePost(postId);
    }

    @PostMapping(value = "/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody Reply reply, @RequestParam String postId) {
        postService.addReply(reply, postId);
    }
}
