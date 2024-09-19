package uz.online.blog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringDocConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Online POST BLOG")
                        .description("Simple Spring Boot Post Blog Application")
                        .version("14")
                        .contact(new Contact()
                                .name("Oybek Saydaliyev")
                                .email("oybeks.pro@gmail.com")
                                .url("https://github.com/oybeksay"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Prod Server")
                )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(( new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                ));
    }

    @Bean
    public GroupedOpenApi grouped1OpenApi() {
        return GroupedOpenApi.builder()
                .group("AuthUsers")
                .pathsToMatch("/api/auth/**")
                .build();
    }
    @Bean
    public GroupedOpenApi grouped2OpenApi() {
        return GroupedOpenApi.builder()
                .group("Comment")
                .pathsToMatch("/comment/**")
                .build();
    }
    @Bean
    public GroupedOpenApi grouped3OpenApi() {
        return GroupedOpenApi.builder()
                .group("Likes")
                .pathsToMatch("/likes/**")
                .build();
    }

    @Bean
    public GroupedOpenApi grouped4OpenApi() {
        return GroupedOpenApi.builder()
                .group("Member")
                .pathsToMatch("/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi grouped5OpenApi() {
        return GroupedOpenApi.builder()
                .group("Post")
                .pathsToMatch("/posts/**")
                .build();
    }
}
