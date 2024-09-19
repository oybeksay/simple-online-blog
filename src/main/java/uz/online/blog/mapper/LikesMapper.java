package uz.online.blog.mapper;

import org.mapstruct.Mapper;
import uz.online.blog.dto.LikesDTO;
import uz.online.blog.entity.Likes;

@Mapper(componentModel = "spring")
public interface LikesMapper {
    Likes fromDto(LikesDTO likeDTO);
}
