package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.model.Poll;
import dev.paulosouza.voting.model.Vote;
import dev.paulosouza.voting.repository.PollRepository;
import dev.paulosouza.voting.repository.VoteRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PublicControllerTest extends AbstractControllerTest {

    private static final String BASE_URI = "/public";

    private static final String VOTE_POLL_ID = "fab0b1fe-3fec-4d4b-8315-6a6ee917ef92";

    private static final String VOTE_USERNAME = "test1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @Transactional
    void voteTest() throws Exception {
        this.mockMvc.perform(
                        patch(BASE_URI + "/polls/" + VOTE_POLL_ID + "/usernames/" + VOTE_USERNAME)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isNoContent());
        this.mockMvc.perform(
                        patch(BASE_URI + "/polls/" + VOTE_POLL_ID + "/usernames/" + VOTE_USERNAME)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isNoContent());

        Poll poll = this.pollRepository.findById(UUID.fromString(VOTE_POLL_ID))
                .orElseThrow();

        Assertions.assertNotNull(poll.getSummarizedVotes());
        Assertions.assertEquals(2, poll.getSummarizedVotes().size());
        Assertions.assertEquals(2, poll.getSummarizedVotes().get(0).getVotes());
        Assertions.assertEquals(VOTE_USERNAME, poll.getSummarizedVotes().get(0).getId().getOption().getUsername());

        List<Vote> votes = this.voteRepository.findByUsername(VOTE_USERNAME);

        Assertions.assertEquals(2, votes.size());

        this.mockMvc.perform(
                        get("/polls/" + VOTE_POLL_ID)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("summarizedVotes", Matchers.hasSize(2)))
                .andExpect(jsonPath("summarizedVotes[0].username").value(VOTE_USERNAME))
                .andExpect(jsonPath("summarizedVotes[0].votes").value(2))
                .andExpect(jsonPath("summarizedVotes[1].votes").value(0));
    }

}
