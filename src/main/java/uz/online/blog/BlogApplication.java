package uz.online.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.online.blog.config.security.SessionUser;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public AuditorAware<Integer> auditorProvider(SessionUser sessionUser) {
		return () -> Optional.of(sessionUser.id());
	}
}
