package dev.paulosouza.voting.service;

import dev.paulosouza.voting.client.ReCaptchaClient;
import dev.paulosouza.voting.dto.response.ReCaptchaResponse;
import dev.paulosouza.voting.exception.NotFoundException;
import dev.paulosouza.voting.exception.UnprocessableEntityException;
import dev.paulosouza.voting.model.Poll;
import dev.paulosouza.voting.model.SummarizedVote;
import dev.paulosouza.voting.model.Vote;
import dev.paulosouza.voting.repository.PollRepository;
import dev.paulosouza.voting.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Slf4j
public class VoteService {

    private final VoteRepository repository;

    private final PollRepository pollRepository;

    private final ReCaptchaClient reCaptchaClient;

    private final boolean recaptchaEnabled;

    private final String recaptchaSecret;

    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    public VoteService(
            VoteRepository repository,
            PollRepository pollRepository,
            ReCaptchaClient reCaptchaClient,
            @Value("${google.recaptcha.enabled}") boolean recaptchaEnabled,
            @Value("${google.recaptcha.key.secret}") String recaptchaSecret
    ) {
        this.repository = repository;
        this.pollRepository = pollRepository;
        this.reCaptchaClient = reCaptchaClient;
        this.recaptchaEnabled = recaptchaEnabled;
        this.recaptchaSecret = recaptchaSecret;
    }

    @Transactional
    public synchronized void vote(UUID pollId, String username, String recaptcha) {
        this.validateCaptcha(recaptcha);

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

    private void validateCaptcha(String recaptcha) {
        if (!this.recaptchaEnabled) {
            return;
        }

        if(!responseSanityCheck(recaptcha)) {
            throw new UnprocessableEntityException("Response contains invalid characters");
        }

        ReCaptchaResponse response = this.reCaptchaClient.get(this.recaptchaSecret, recaptcha);

        if(!response.isSuccess()) {
            throw new UnprocessableEntityException("reCaptcha was not successfully validated");
        }
    }

    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

}
