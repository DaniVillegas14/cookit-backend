package ar.edu.unq.cookitbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class CookitBackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CookitBackendApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE")
				.allowedOrigins("*")
				.allowedHeaders("*")
				.allowCredentials(true);
	}

	@Bean
	// Swagger config bean.
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("ar.edu.unq.cookitbackend")).build();
	}
}
