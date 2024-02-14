package dev.paulosouza.voting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.paulosouza.voting.config.PostgresContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Base64;

@ActiveProfiles(profiles = "test")
@Testcontainers
public abstract class AbstractControllerTest {

	@Container
	private static final PostgresContainer POSTGRES_CONTAINER = PostgresContainer.getInstance();

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${application.basic.user}")
	private String basicUser;

	@Value("${application.basic.password}")
	private String basicPassword;

	protected String toJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	protected <T> T toObject(String json, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.readValue(json, clazz);
	}

	protected String getBasicHeader() {
		return "Basic " + new String(Base64.getEncoder().encode((basicUser + ":" + basicPassword).getBytes()));
	}

}
