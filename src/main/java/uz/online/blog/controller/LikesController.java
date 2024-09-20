package uz.online.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping("/create")
    public ResponseEntity<Likes> addLike(@RequestBody LikesDTO likesDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likesService.addLike(likesDTO));
    }

    @Operation(summary = "Get likes count by post id")
    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> countLike(@PathVariable Integer postId) {
        return ResponseEntity.ok(likesService.getLikesCountByPostId(postId));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "delete like by postId and userId")
    public ResponseEntity<Void> deleteLike(@RequestParam Integer postId, @RequestParam Integer userId) {
        likesService.deleteLikesById(postId,userId);
        return ResponseEntity.noContent().build();
    }
}
