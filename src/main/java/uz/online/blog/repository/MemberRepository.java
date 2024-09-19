package uz.online.blog.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.online.blog.entity.Member;

import java.util.Optional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

    void deleteByUsername(String username);
}