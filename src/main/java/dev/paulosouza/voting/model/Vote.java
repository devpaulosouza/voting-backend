package dev.paulosouza.voting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "username")
    private Option option;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

}
