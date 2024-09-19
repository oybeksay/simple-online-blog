package uz.online.blog.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.online.blog.entity.auth.AuthUser;
import uz.online.blog.service.AuthUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserService authUserService;

    public CustomUserDetailsService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserService.findByUsername(username);
        return new CustomUserDetails(authUser);
    }
}
