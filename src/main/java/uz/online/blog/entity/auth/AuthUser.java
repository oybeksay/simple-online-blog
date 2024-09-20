package uz.online.blog.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import uz.online.blog.domains.Role;
import uz.online.blog.entity.Auditable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AuthUser extends Auditable {
    @Column(unique = true, nullable = false)
    private String username;
    @Email
    @Column(unique = true,  nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
}
