package dev.paulosouza.voting.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PollRequest {

    private String title;

    private String subtitle;

    private List<String> usernames;

}
