package uz.online.blog.mapper;

import org.mapstruct.Mapper;
import uz.online.blog.dto.CommentDTO;
import uz.online.blog.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment fromDto(CommentDTO commentDTO);
}
