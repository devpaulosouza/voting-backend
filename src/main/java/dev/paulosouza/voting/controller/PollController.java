package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.dto.request.PollRequest;
import dev.paulosouza.voting.dto.response.PollResponse;
import dev.paulosouza.voting.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/polls")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class PollController {

    private final PollService service;

    @Operation(description = "Creates a new poll")
    @ApiResponse(responseCode = "201", description = "Successfully created")
    @PostMapping
    public ResponseEntity<PollResponse> create(@Valid @RequestBody PollRequest request) {
        PollResponse response = this.service.create(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Stops a poll")
    @ApiResponse(responseCode = "204", description = "Successfully stopped")
    @PatchMapping("/{pollId}/stop")
    public ResponseEntity<Void> stop(@PathVariable("pollId") UUID pollId) {
        this.service.stop(pollId);

        return ResponseEntity.noContent().build();
    }

}
