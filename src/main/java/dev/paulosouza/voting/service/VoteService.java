package dev.paulosouza.voting.service;

import dev.paulosouza.voting.exception.NotFoundException;
import dev.paulosouza.voting.exception.UnprocessableEntityException;
import dev.paulosouza.voting.model.Poll;
import dev.paulosouza.voting.model.SummarizedVote;
import dev.paulosouza.voting.model.Vote;
import dev.paulosouza.voting.repository.PollRepository;
import dev.paulosouza.voting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository repository;

    private final PollRepository pollRepository;

    @Transactional
    public synchronized void vote(UUID pollId, String username) {
        Poll poll = this.pollRepository.findById(pollId)
                .orElseThrow(() -> new NotFoundException(pollId));

        SummarizedVote summarizedVote = poll.getSummarizedVotes()
                .stream()
                .filter(vote -> username.equalsIgnoreCase(vote.getId().getOption().getUsername()))
                .findFirst()
                .orElseThrow(() -> new UnprocessableEntityException(String.format("Option with username %s was not found", username)));

        summarizedVote.setVotes(summarizedVote.getVotes() + 1);

        Vote vote = new Vote(summarizedVote.getId().getOption(), summarizedVote.getId().getPoll());

        this.repository.save(vote);
    }

}
