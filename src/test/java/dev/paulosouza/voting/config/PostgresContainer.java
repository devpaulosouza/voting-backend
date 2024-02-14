package dev.paulosouza.voting.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {

	private static PostgresContainer container;

	private PostgresContainer() {
		super("postgres:latest");
	}

	public static PostgresContainer getInstance() {
		if (container == null) {
			container = new PostgresContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("DATABASE_URL", container.getJdbcUrl());
		System.setProperty("DB_USERNAME", container.getUsername());
		System.setProperty("DB_PASSWORD", container.getPassword());
	}

	@Override
	public void stop() {
	}

}
