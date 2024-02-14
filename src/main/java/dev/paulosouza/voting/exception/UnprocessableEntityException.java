package dev.paulosouza.voting.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class UnprocessableEntityException extends AbstractThrowableProblem {

	static final URI TYPE = URI.create("https://paulosouza.dev/unprocessable-entity");

	private final String cause;

	public UnprocessableEntityException(String cause) {
		super(
				TYPE,
				"Unprocessable Entity",
				Status.UNPROCESSABLE_ENTITY,
				cause
		);
		this.cause = cause;
	}

}
