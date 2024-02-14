package dev.paulosouza.voting.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "polls")
public class Poll extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    private String title;

    private String subtitle;

    private boolean stopped;

    @ManyToMany
    @JoinTable(
            name = "polls_options",
            joinColumns = @JoinColumn(name = "poll_id"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private List<Option> options;

    @OneToMany(mappedBy = "id.poll", cascade = { CascadeType.ALL })
    @OrderBy("created_at DESC")
    private List<SummarizedVote> summarizedVotes;

}
