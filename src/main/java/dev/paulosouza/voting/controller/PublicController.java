package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final VoteService voteService;

    @PatchMapping("/polls/{pollId}/usernames/{username}")
    public ResponseEntity<Void> vote(
            @PathVariable("pollId") UUID pollId,
            @PathVariable("username") String username
    ) {
        this.voteService.vote(pollId, username);

        return ResponseEntity.noContent().build();
    }

}
