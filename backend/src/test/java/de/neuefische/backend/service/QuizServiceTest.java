package de.neuefische.backend.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void when_getCategories_returnRightBody() throws Exception {
        //Given
        String responseBody = """
                        {
                            "trivia_categories": [
                                {
                                    "id": 9,
                                    "name": "General Knowledge"
                                },
                                {
                                    "id": 10,
                                    "name": "Entertainment: Books"
                                },
                                {
                                    "id": 11,
                                    "name": "Entertainment: Film"
                                },
                                {
                                    "id": 12,
                                    "name": "Entertainment: Music"
                                },
                                {
                                    "id": 13,
                                    "name": "Entertainment: Musicals & Theatres"
                                },
                                {
                                    "id": 14,
                                    "name": "Entertainment: Television"
                                },
                                {
                                    "id": 15,
                                    "name": "Entertainment: Video Games"
                                },
                                {
                                    "id": 16,
                                    "name": "Entertainment: Board Games"
                                },
                                {
                                    "id": 17,
                                    "name": "Science & Nature"
                                },
                                {
                                    "id": 18,
                                    "name": "Science: Computers"
                                },
                                {
                                    "id": 19,
                                    "name": "Science: Mathematics"
                                },
                                {
                                    "id": 20,
                                    "name": "Mythology"
                                },
                                {
                                    "id": 21,
                                    "name": "Sports"
                                },
                                {
                                    "id": 22,
                                    "name": "Geography"
                                },
                                {
                                    "id": 23,
                                    "name": "History"
                                },
                                {
                                    "id": 24,
                                    "name": "Politics"
                                },
                                {
                                    "id": 25,
                                    "name": "Art"
                                },
                                {
                                    "id": 26,
                                    "name": "Celebrities"
                                },
                                {
                                    "id": 27,
                                    "name": "Animals"
                                },
                                {
                                    "id": 28,
                                    "name": "Vehicles"
                                },
                                {
                                    "id": 29,
                                    "name": "Entertainment: Comics"
                                },
                                {
                                    "id": 30,
                                    "name": "Science: Gadgets"
                                },
                                {
                                    "id": 31,
                                    "name": "Entertainment: Japanese Anime & Manga"
                                },
                                {
                                    "id": 32,
                                    "name": "Entertainment: Cartoon & Animations"
                                }
                            ]
                        }
                        """;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(responseBody));


    }

}