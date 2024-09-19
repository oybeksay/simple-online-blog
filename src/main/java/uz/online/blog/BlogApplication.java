package uz.online.blog;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.online.blog.config.security.SessionUser;
import uz.online.blog.entity.Post;
import uz.online.blog.repository.PostRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	/*@Bean*/
	public CommandLineRunner commandLineRunner(ApplicationContext ctx, ObjectMapper objectMapper, PostRepository postRepository) {
		return args -> {
			URI uri = new URI("https://jsonplaceholder.typicode.com/posts");
			List<Post> posts = objectMapper.readValue(uri.toURL(), new TypeReference<>() {});
			postRepository.saveAll(posts);
		};
	}

	@Bean
	public AuditorAware<Integer> auditorProvider(SessionUser sessionUser) {
		return () -> Optional.of(sessionUser.id());
	}
}
