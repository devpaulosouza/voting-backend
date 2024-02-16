package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.dto.response.ResumedPollResponse;
import dev.paulosouza.voting.service.PollService;
import dev.paulosouza.voting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final VoteService voteService;

    private final PollService pollService;

    @PatchMapping("/polls/{pollId}/usernames/{username}")
    public ResponseEntity<Void> vote(
            @PathVariable("pollId") UUID pollId,
            @PathVariable("username") String username,
            @RequestHeader("recaptcha") String recaptcha
    ) {
        this.voteService.vote(pollId, username, recaptcha);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/polls")
    public ResponseEntity<Page<ResumedPollResponse>> getPolls(Pageable pageable) {
        Page<ResumedPollResponse> response = this.pollService.findAllResumed(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<ResumedPollResponse> getPolls(@PathVariable("pollId") UUID pollId) {
        ResumedPollResponse response = this.pollService.findResumed(pollId);

        return ResponseEntity.ok(response);
    }

}
