package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.dto.request.PollRequest;
import dev.paulosouza.voting.dto.response.PollResponse;
import dev.paulosouza.voting.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Operation(description = "Gets a poll")
    @ApiResponse(responseCode = "200", description = "Poll found")
    @ApiResponse(responseCode = "404", description = "Poll was not found")
    @GetMapping("/{pollId}")
    public ResponseEntity<PollResponse> get(@PathVariable("pollId") UUID pollId) {
        PollResponse response = this.service.find(pollId);

        return ResponseEntity.ok(response);
    }

    @Operation(description = "Gets a poll")
    @ApiResponse(responseCode = "200", description = "Polls")
    @GetMapping
    public ResponseEntity<Page<PollResponse>> getAll(Pageable pageable) {
        Page<PollResponse> response = this.service.findAll(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(description = "Stops a poll")
    @ApiResponse(responseCode = "204", description = "Successfully stopped")
    @PatchMapping("/{pollId}/stop")
    public ResponseEntity<Void> stop(@PathVariable("pollId") UUID pollId) {
        this.service.stop(pollId);

        return ResponseEntity.noContent().build();
    }


    @Operation(description = "Deletes a poll")
    @ApiResponse(responseCode = "204", description = "Poll deleted")
    @DeleteMapping("/{pollId}")
    public ResponseEntity<Void> delete(@PathVariable("pollId") UUID pollId) {
        this.service.delete(pollId);

        return ResponseEntity.noContent().build();
    }

}
