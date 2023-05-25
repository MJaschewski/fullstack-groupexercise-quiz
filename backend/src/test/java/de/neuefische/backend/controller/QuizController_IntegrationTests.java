package de.neuefische.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class QuizController_IntegrationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void getMapping_getCategoryList_return_200Ok_return_Categories() throws Exception {
        //Given
        String apiResponse = """ 
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
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(apiResponse));
    }

    @Test
    void shouldReturnAllCategoriesFromApiAndStatus200() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("https://opentdb.com").build();
        webTestClient
                .get()
                .uri("/api_category.php")
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.trivia_categories[0].name").isEqualTo("General Knowledge");
    }

}