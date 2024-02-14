package dev.paulosouza.voting.model.id;

import dev.paulosouza.voting.model.Option;
import dev.paulosouza.voting.model.Poll;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SummarizedVoteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "username")
    private Option option;

}
