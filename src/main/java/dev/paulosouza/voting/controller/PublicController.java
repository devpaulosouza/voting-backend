package dev.paulosouza.voting.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games/admin/bingo")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
public class PublicController {
}
