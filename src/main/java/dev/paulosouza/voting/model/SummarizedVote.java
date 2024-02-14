package dev.paulosouza.voting.model;


import dev.paulosouza.voting.model.id.SummarizedVoteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "summarized_votes")
public class SummarizedVote {

    @EmbeddedId
    private SummarizedVoteId id;

    private long votes;

}
