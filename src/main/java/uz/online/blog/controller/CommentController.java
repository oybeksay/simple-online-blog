package uz.online.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.online.blog.dto.CommentDTO;
import uz.online.blog.entity.Comment;
import uz.online.blog.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> addComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(commentDTO));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Integer id) {
        commentService.deleteCommentByPostIdAndUserId(id);
        return ResponseEntity.noContent().build();
    }
}
