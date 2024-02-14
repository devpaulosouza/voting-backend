package dev.paulosouza.voting.repository;

import dev.paulosouza.voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {

    @Query(
            """
            FROM Vote v
            INNER JOIN v.option o
            WHERE o.username = :username
            """
    )
    List<Vote> findByUsername(String username);

}
