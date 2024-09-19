package uz.online.blog.mapper;

import org.mapstruct.Mapper;
import uz.online.blog.dto.AuthUserDTO;
import uz.online.blog.entity.auth.AuthUser;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUser toAuthUser(AuthUserDTO authUserDTO);

}
