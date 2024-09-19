package uz.online.blog.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.online.blog.domains.Role;
import uz.online.blog.dto.AuthUserDTO;
import uz.online.blog.entity.auth.AuthUser;
import uz.online.blog.mapper.AuthUserMapper;
import uz.online.blog.repository.AuthUserRepository;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserMapper authUserMapper;

    public AuthUserServiceImpl(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, AuthUserMapper authUserMapper) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUserMapper = authUserMapper;
    }

    @Override
    public AuthUser save(AuthUserDTO dto) {
        AuthUser authUser = authUserMapper.toAuthUser(dto);
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return authUserRepository.save(authUser);
    }

    @Override
    public AuthUser findByUsername(String username) {
        return authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public void update(AuthUserDTO dto, Role role) {
        authUserRepository.updateRoleByUsernameAndEmail(role,dto.getUsername(),dto.getEmail());
    }

}
