package dev.paulosouza.voting.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
	type = SecuritySchemeType.HTTP,
	name = "basicAuth",
	scheme = "basic"
)
public class SpringFoxConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.group("voting")
				.pathsToMatch("/**")
				.packagesToScan("dev.paulosouza.voting.controller")
				.build();
	}

}