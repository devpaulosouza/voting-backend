package dev.paulosouza.voting.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class SummarizedVoteResponse {

    private UUID pollId;

    private String username;

    private long votes;

}
