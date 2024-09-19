package uz.online.blog.service;

import uz.online.blog.domains.Role;
import uz.online.blog.dto.AuthUserDTO;
import uz.online.blog.entity.auth.AuthUser;

public interface AuthUserService {

    AuthUser save(AuthUserDTO dto);

    AuthUser findByUsername(String username);

    void update(AuthUserDTO dto, Role role);
}
