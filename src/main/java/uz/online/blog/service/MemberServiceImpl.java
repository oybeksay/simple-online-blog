package uz.online.blog.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.online.blog.dto.MemberDTO;
import uz.online.blog.entity.Member;
import uz.online.blog.mapper.MemberMapper;
import uz.online.blog.repository.MemberRepository;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }

    @Override
    public Member save(MemberDTO memberDTO) {
        Member member = memberMapper.toMember(memberDTO);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    @Override
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void delete(String username) {
        memberRepository.deleteByUsername(username);
    }

    @Override
    public Member update(Member user) {
        return null;
    }

    @Override
    public String checkUser(String username, String password) {
        Member member = findByUsername(username);
        if (passwordEncoder.matches(password, member.getPassword())) {
            return "Success";
        }
        return "Username or password is incorrect";
    }

    @Override
    public Member updateUserPassword(Integer userId, String password) {
        Member user = memberRepository.findById(userId).get();
        user.setPassword(passwordEncoder.encode(password));
        return memberRepository.save(user);
    }
}
