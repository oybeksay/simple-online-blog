package uz.online.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.online.blog.config.security.jwt.JwtTokenUtil;
import uz.online.blog.domains.Role;
import uz.online.blog.dto.AuthUserDTO;
import uz.online.blog.dto.TokenRequest;
import uz.online.blog.entity.auth.AuthUser;
import uz.online.blog.service.AuthUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

    private final AuthUserService authUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthUserController(AuthUserService authUserService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.authUserService = authUserService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody AuthUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authUserService.save(dto));
    }

    @PostMapping("/token")
    public String getToken(@RequestBody TokenRequest tokenRequest) {
        String username = tokenRequest.username();
        String password = tokenRequest.password();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenUtil.generateToken(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Void> updateRole(@RequestBody AuthUserDTO dto ,Role role) {
        authUserService.update(dto,role);
        return ResponseEntity.noContent().build();
    }
}
