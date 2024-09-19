package uz.online.blog.mapper;

import org.mapstruct.Mapper;
import uz.online.blog.dto.PostDTO;
import uz.online.blog.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post fromDto(PostDTO postDTO);
}
