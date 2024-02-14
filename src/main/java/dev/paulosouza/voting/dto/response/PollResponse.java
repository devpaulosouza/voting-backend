package dev.paulosouza.voting.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PollResponse {

    private UUID id;

    private String title;

    private String subtitle;

    private boolean stopped;

    private List<OptionResponse> options;

}
