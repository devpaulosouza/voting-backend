package dev.paulosouza.voting.service;

import dev.paulosouza.voting.model.Vote;
import dev.paulosouza.voting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository repository;

    public synchronized void vote(UUID pollId, String username) {
        Vote entity = new Vote();
    }

}
