package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.dto.request.OptionRequest;
import dev.paulosouza.voting.dto.response.OptionResponse;
import dev.paulosouza.voting.service.OptionService;
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
@RequestMapping("/options")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService service;

    @Operation(description = "Creates a new option")
    @ApiResponse(responseCode = "201", description = "Successfully created")
    @PostMapping
    public ResponseEntity<OptionResponse> create(@Valid @RequestBody OptionRequest request) {
        OptionResponse response = this.service.create(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Gets an option")
    @ApiResponse(responseCode = "200", description = "Option found")
    @ApiResponse(responseCode = "404", description = "Option was not found")
    @GetMapping("/{username}")
    public ResponseEntity<OptionResponse> get(@PathVariable("username") String username) {
        OptionResponse response = this.service.find(username);

        return ResponseEntity.ok(response);
    }

    @Operation(description = "Gets an option page")
    @ApiResponse(responseCode = "200", description = "Options")
    @GetMapping
    public ResponseEntity<Page<OptionResponse>> getAll(Pageable pageable) {
        Page<OptionResponse> response = this.service.findAll(pageable);

        return ResponseEntity.ok(response);
    }


    @Operation(description = "Deletes an option")
    @ApiResponse(responseCode = "204", description = "Option deleted")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable("username") String username) {
        this.service.delete(username);

        return ResponseEntity.noContent().build();
    }

}
