package dev.paulosouza.voting.controller;

import dev.paulosouza.voting.dto.request.OptionRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class OptionControllerTest extends AbstractControllerTest {

    private static final String BASE_URI = "/options";

    private static final String GET_ID = "test1";

    private static final String NOT_FOUND_ID = "not-fount";

    private static final String DELETE_ID = "delete";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        OptionRequest request = new OptionRequest();

        request.setName("Test create");
        request.setUsername("create");

        ResultActions resultActions = this.mockMvc.perform(
                        post(BASE_URI)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                                .content(this.toJson(request))
                )
                .andExpect(status().isCreated());

        matchResult(resultActions, request);
    }

    @Test
    void find() throws Exception {
        this.mockMvc.perform(
                        get(BASE_URI + "/" + GET_ID)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Test1"))
                .andExpect(jsonPath("username").value("test1"));
    }

    @Test
    void findNotFound() throws Exception {
        this.mockMvc.perform(
                        get(BASE_URI + "/" + NOT_FOUND_ID)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteById() throws Exception {
        this.mockMvc.perform(
                        delete(BASE_URI + "/" + DELETE_ID)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNotFound() throws Exception {
        this.mockMvc.perform(
                        get(BASE_URI + "/" + NOT_FOUND_ID)
                                .header(HttpHeaders.AUTHORIZATION, getBasicHeader())
                                .contentType("application/json")
                )
                .andExpect(status().isNotFound());
    }

    private void matchResult(ResultActions resultActions, OptionRequest request) throws Exception {
        resultActions
                .andExpect(jsonPath("name").value(request.getName()))
                .andExpect(jsonPath("username").value(request.getUsername()));
    }

}
