package dev.paulosouza.voting.dto.request;

import lombok.Data;

@Data
public class ReCaptchaRequest {

    private String secret;

    private String response;

}
