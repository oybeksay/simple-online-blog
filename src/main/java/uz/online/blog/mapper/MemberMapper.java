package uz.online.blog.mapper;

import org.mapstruct.Mapper;
import uz.online.blog.dto.MemberDTO;
import uz.online.blog.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toMember(MemberDTO memberDTO);
}
