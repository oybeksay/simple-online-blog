package uz.online.blog.service;

import uz.online.blog.dto.MemberDTO;
import uz.online.blog.entity.Member;

import java.util.List;

public interface MemberService {

    Member save(MemberDTO memberDTO);

    Member findByUsername(String username);

    void delete(String username);

    Member update(Member user);

    String checkUser(String username, String password);

    Member updateUserPassword(Integer userId, String password);
}
