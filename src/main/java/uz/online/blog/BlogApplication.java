package uz.online.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.online.blog.config.security.SessionUser;
import uz.online.blog.domains.Role;
import uz.online.blog.entity.auth.AuthUser;
import uz.online.blog.repository.AuthUserRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class BlogApplication {

	@Value("${admins.username}")
	private String username;

	@Value("${admins.password}")
	private String password;

	@Value("${admins.email}")
	private String email;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx, AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			AuthUser authUser = new AuthUser();
			authUser.setUsername(username);
			authUser.setPassword(passwordEncoder.encode(password));
			authUser.setRole(Role.ROLE_ADMIN);
			authUser.setEmail(email);
			authUserRepository.save(authUser);
		};
	}

	@Bean
	public AuditorAware<Integer> auditorProvider(SessionUser sessionUser) {
		return () -> Optional.of(sessionUser.id());
	}
}
