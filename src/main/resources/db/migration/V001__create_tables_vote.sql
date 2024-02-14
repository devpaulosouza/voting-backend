CREATE TABLE options
(
	username VARCHAR(20) PRIMARY KEY NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT Now(),
	updated_at TIMESTAMP NOT NULL DEFAULT Now(),
	name VARCHAR(14) NOT NULL
);

CREATE TABLE polls
(
	id UUID PRIMARY KEY,
	created_at TIMESTAMP NOT NULL DEFAULT Now(),
	updated_at TIMESTAMP NOT NULL DEFAULT Now(),
	title VARCHAR(50) NOT NULL,
	subtitle VARCHAR(1000) NOT NULL,
	stopped BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE polls_options
(
    poll_id UUID NOT NULL,
    username VARCHAR(20) NOT NULL,
    FOREIGN KEY (poll_id) REFERENCES polls(id),
    FOREIGN KEY (username) REFERENCES options(username)
);

CREATE TABLE summarized_votes
(
    poll_id UUID NOT NULL,
    username VARCHAR(50) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT Now(),
	updated_at TIMESTAMP NOT NULL DEFAULT Now(),
    votes BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY(poll_id, username),
    FOREIGN KEY (poll_id) REFERENCES polls(id),
    FOREIGN KEY (username) REFERENCES options(username)
);

CREATE TABLE votes
(
	id UUID PRIMARY KEY,
	created_at TIMESTAMP NOT NULL DEFAULT Now(),
	updated_at TIMESTAMP NOT NULL DEFAULT Now(),
    username VARCHAR(20) NOT NULL,
    poll_id UUID NOT NULL,
    FOREIGN KEY (poll_id) REFERENCES polls(id),
    FOREIGN KEY (username) REFERENCES options(username)
);
