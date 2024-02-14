package dev.paulosouza.voting.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.UUID;

public class NotFoundException extends AbstractThrowableProblem {

	static final URI TYPE = URI.create("https://paulosouza.dev/not-found");

	public NotFoundException(final UUID id) {
		super(
				TYPE,
				"Not found",
				Status.NOT_FOUND,
				String.format("Entity with id %s was not found", id)
		);
	}

	public NotFoundException(final String id) {
		super(
				TYPE,
				"Not found",
				Status.NOT_FOUND,
				String.format("Entity with id %s was not found", id)
		);
	}

}
