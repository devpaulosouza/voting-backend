package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.dto.request.PollRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PollControllerTest extends AbstractControllerTest {

    private static final String BASE_URI = "/polls";

    private static final String STOP_ID = "9a8537ac-d939-4ab8-844c-2254087757e5";

    private static final String STOPPED_ID = "ce23f269-4dff-4171-aff8-5397af6e9828";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        PollRequest request = new PollRequest();

        request.setTitle("Poll test create");
        request.setSubtitle("Subtitle");
        request.setUsernames(List.of("test1", "test2"));

        ResultActions resultActions = this.mockMvc.perform(
                        post(BASE_URI)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                                .content(this.toJson(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty());

        matchResult(resultActions, request);
    }

    @Test
    void stop() throws Exception {
        this.mockMvc.perform(
                        patch(BASE_URI + "/" + STOP_ID + "/stop")
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void alreadyStopped() throws Exception {
        this.mockMvc.perform(
                        patch(BASE_URI + "/" + STOPPED_ID + "/stop")
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    private void matchResult(ResultActions resultActions, PollRequest request) throws Exception {
        resultActions
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("subtitle").value(request.getSubtitle()))
                .andExpect(jsonPath("stopped").value("false"))
                .andExpect(jsonPath("options", Matchers.hasSize(request.getUsernames().size())));
    }

}
