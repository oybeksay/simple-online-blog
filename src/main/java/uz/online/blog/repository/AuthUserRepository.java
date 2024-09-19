package uz.online.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.online.blog.domains.Role;
import uz.online.blog.entity.auth.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.role = ?1 where a.username = ?2 and a.email = ?3")
    void updateRoleByUsernameAndEmail(Role role, String username, String email);
}