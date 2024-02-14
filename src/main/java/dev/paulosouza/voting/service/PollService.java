package dev.paulosouza.voting.service;

import dev.paulosouza.voting.dto.request.PollRequest;
import dev.paulosouza.voting.dto.response.PollResponse;
import dev.paulosouza.voting.exception.NotFoundException;
import dev.paulosouza.voting.exception.UnprocessableEntityException;
import dev.paulosouza.voting.mapper.PollMapper;
import dev.paulosouza.voting.model.Option;
import dev.paulosouza.voting.model.Poll;
import dev.paulosouza.voting.model.SummarizedVote;
import dev.paulosouza.voting.model.id.SummarizedVoteId;
import dev.paulosouza.voting.repository.OptionRepository;
import dev.paulosouza.voting.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PollService {

    private final PollRepository repository;

    private final OptionRepository optionRepository;

    public PollResponse create(PollRequest request) {
        log.info("creating poll {}", request);
        Poll entity = PollMapper.INSTANCE.toEntity(request);

        entity.setOptions(request.getUsernames().stream().map(this::getOption).toList());
        entity.setSummarizedVotes(entity.getOptions().stream().map(option -> this.buildSummarizedVotes(entity, option)).toList());

        this.repository.save(entity);

        return PollMapper.INSTANCE.toResponse(entity);
    }

    public void stop(UUID id) {
        Poll entity = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        if (entity.isStopped()) {
            throw new UnprocessableEntityException("Poll is already stopped");
        }

        entity.setStopped(true);

        this.repository.save(entity);
    }

    private SummarizedVote buildSummarizedVotes(Poll poll, Option option) {
        return SummarizedVote.builder()
                .id(new SummarizedVoteId(poll, option))
                .votes(0)
                .build();
    }

    private Option getOption(String username) {
        return this.optionRepository.findById(username)
                .orElseThrow(() -> new UnprocessableEntityException(String.format("Option with username %s was not found", username)));
    }
}
