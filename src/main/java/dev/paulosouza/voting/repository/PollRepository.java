package dev.paulosouza.voting.repository;

import dev.paulosouza.voting.model.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PollRepository extends JpaRepository<Poll, UUID> {

    Page<Poll> findAllByStoppedIsFalse(Pageable pageable);

}
