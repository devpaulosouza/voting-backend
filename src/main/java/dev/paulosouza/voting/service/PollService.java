package dev.paulosouza.voting.service;

import dev.paulosouza.voting.dto.request.PollRequest;
import dev.paulosouza.voting.dto.response.PollResponse;
import dev.paulosouza.voting.dto.response.ResumedPollResponse;
import dev.paulosouza.voting.exception.NotFoundException;
import dev.paulosouza.voting.exception.UnprocessableEntityException;
import dev.paulosouza.voting.mapper.PollMapper;
import dev.paulosouza.voting.model.Option;
import dev.paulosouza.voting.model.Poll;
import dev.paulosouza.voting.model.SummarizedVote;
import dev.paulosouza.voting.model.id.SummarizedVoteId;
import dev.paulosouza.voting.repository.OptionRepository;
import dev.paulosouza.voting.repository.PollRepository;
import dev.paulosouza.voting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PollService {

    private final PollRepository repository;

    private final OptionRepository optionRepository;

    private final VoteRepository voteRepository;

    public PollResponse create(PollRequest request) {
        log.info("creating poll {}", request);
        Poll entity = PollMapper.INSTANCE.toEntity(request);

        entity.setOptions(request.getUsernames().stream().map(this::getOption).toList());
        entity.setSummarizedVotes(entity.getOptions().stream().map(option -> this.buildSummarizedVotes(entity, option)).toList());

        this.repository.save(entity);

        return PollMapper.INSTANCE.toResponse(entity);
    }

    public PollResponse find(UUID id) {
        Poll entity = this.getPoll(id);

        return PollMapper.INSTANCE.toResponse(entity);
    }

    public Page<PollResponse> findAll(Pageable pageable) {
        Page<Poll> entities = this.repository.findAll(pageable);

        return entities.map(PollMapper.INSTANCE::toResponse);
    }

    public ResumedPollResponse findResumed(UUID id) {
        Poll entity = this.getPoll(id);

        return PollMapper.INSTANCE.toResumedResponse(entity);
    }

    public Page<ResumedPollResponse> findAllResumed(Pageable pageable) {
        Page<Poll> entities = this.repository.findAll(pageable);

        return entities.map(PollMapper.INSTANCE::toResumedResponse);
    }

    public void stop(UUID id) {
        Poll entity = getPoll(id);

        if (entity.isStopped()) {
            throw new UnprocessableEntityException("Poll is already stopped");
        }

        entity.setStopped(true);

        this.repository.save(entity);
    }

    public void delete(UUID id) {
        Poll poll = this.getPoll(id);

        this.voteRepository.deleteAllByPollId(id);
        this.repository.delete(poll);
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

    private Poll getPoll(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }
}
