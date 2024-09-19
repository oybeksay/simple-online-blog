package uz.online.blog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.online.blog.dto.PostDTO;
import uz.online.blog.entity.Post;
import uz.online.blog.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@PreAuthorize("isAuthenticated()")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> getPostByTitle(@RequestParam(name = "query") String query) {
        return ResponseEntity.ok(postService.finByTitleOrDestcription(query));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Post>> getPosts(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size
            )
    {
        Page<Post> pageBySize = postService.getPageBySize(PageRequest.of(page, size));
        PagedModel<Post> postPagedModel = new PagedModel<>(pageBySize);
        return ResponseEntity.ok(postPagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.update(id, postDTO));
    }

}
