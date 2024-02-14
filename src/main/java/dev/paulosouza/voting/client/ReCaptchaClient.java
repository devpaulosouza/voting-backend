package dev.paulosouza.voting.client;

import dev.paulosouza.voting.dto.response.ReCaptchaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "reCaptcha", url = "https://www.google.com/recaptcha/api/siteverify")
public interface ReCaptchaClient {

    @GetMapping
    ReCaptchaResponse get(@RequestParam(name = "secret") String secret, @RequestParam(name = "response") String reCaptcha);

}
