package dev.paulosouza.voting.client;

import dev.paulosouza.voting.dto.request.ReCaptchaRequest;
import dev.paulosouza.voting.dto.response.ReCaptchaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "reCaptcha", url = "https://www.google.com/recaptcha/api/siteverify")
public interface ReCaptchaClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ReCaptchaResponse post(@RequestBody ReCaptchaRequest request);

}
