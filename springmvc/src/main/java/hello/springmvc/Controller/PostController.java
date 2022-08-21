package hello.springmvc.Controller;

import hello.springmvc.entity.Post;
import hello.springmvc.entity.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPost(@RequestBody Map<String, Object> requestBody) {
        Post post = Post.builder().title(requestBody.get("title").toString())
                .contents(requestBody.get("contents").toString()).build();

        postRepository.save(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "")
    public String getPostList(Model model) {

        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    //게시글 추가/수정 페이지
    @GetMapping(value = "/add-post-page")
    public String getAddPostPage(@RequestParam(value = "state", required = false, defaultValue = "create") String state,
                                 @RequestParam(value = "postId", required = false) Long postId, Model model) {
        if (state.equals("update")) {
            Post post = postRepository.findById(postId).get();
            model.addAttribute("post", post);
        }

        model.addAttribute("state", state);
        model.addAttribute("postId", postId);

        return "add-post-page";
    }

    @GetMapping(value = "/{postId}")
    public Optional<Post> getPost(@PathVariable Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        return post;
    }

    @PutMapping(value = "/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePost(
            @PathVariable Long postId, @RequestBody Map<String, Object> requestBody
    ) {
        Post post = Post.builder().postId(postId)
                .title(requestBody.get("title").toString())
                .contents(requestBody.get("contents").toString())
                .build();
        postRepository.save(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId
    ) {
        Post post = Post.builder().postId(postId).build();
        postRepository.delete(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
