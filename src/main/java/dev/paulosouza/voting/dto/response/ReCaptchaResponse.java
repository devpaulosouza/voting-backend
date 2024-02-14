package dev.paulosouza.voting.dto.response;

import lombok.Data;

@Data
public class ReCaptchaResponse {

    private boolean success;

    private double score;

}
