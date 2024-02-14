package dev.paulosouza.voting.model;


import dev.paulosouza.voting.model.id.SummarizedVoteId;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "summarized_votes")
public class SummarizedVote extends AuditedEntity {

    @EmbeddedId
    private SummarizedVoteId id;

    private long votes;

}
