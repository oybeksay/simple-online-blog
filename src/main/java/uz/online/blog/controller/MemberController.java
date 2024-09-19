package uz.online.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.online.blog.dto.MemberDTO;
import uz.online.blog.entity.Member;
import uz.online.blog.service.MemberService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> register(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(memberDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return ResponseEntity.ok(memberService.checkUser(username,password));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Member> getUser(@PathVariable String username) {
        return ResponseEntity.ok(memberService.findByUsername(username));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        memberService.delete(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/password/{userId}")
    public ResponseEntity<Member> updatePassword(@PathVariable Integer userId, @RequestBody String password) {
        return ResponseEntity.ok(memberService.updateUserPassword(userId,password));
    }

}
