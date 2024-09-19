package uz.online.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.online.blog.dto.LikesDTO;
import uz.online.blog.entity.Likes;
import uz.online.blog.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/add")
    public ResponseEntity<Likes> addLike(@RequestBody LikesDTO likesDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likesService.addLike(likesDTO));
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> countLike(@PathVariable Integer postId) {
        return ResponseEntity.ok(likesService.getLikesCountByPostId(postId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Integer id) {
        likesService.deleteLikesById(id);
        return ResponseEntity.noContent().build();
    }
}
