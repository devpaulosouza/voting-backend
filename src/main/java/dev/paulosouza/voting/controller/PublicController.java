package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final VoteService voteService;

    @PostMapping("/polls/{pollId}/usernames/{username}")
    public ResponseEntity<Void> vote(
            @PathVariable("pollId") UUID pollId,
            @PathVariable("username") String username
    ) {
        this.voteService.vote(pollId, username);

        return ResponseEntity.noContent().build();
    }

}
