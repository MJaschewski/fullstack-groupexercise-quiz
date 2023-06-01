package de.neuefische.backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.QuestionUnsorted;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Duration;
import java.util.List;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
    void shouldReturnAllCategoriesFromApi_Status200() {
        this.webTestClient = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(15)).baseUrl("https://opentdb.com").build();
        webTestClient
                .get()
                .uri("/api_category.php")
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody()
                .json("""
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
                                            """);
    }

    @Test
    void postMapping_postQuizSession_return_200Ok_returns_true() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/home")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                   {
                                       "questions":"3"
                                        ,"category":"General Knowledge"
                                        ,"difficulty":"easy"
                                    }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    @DirtiesContext
    void getMapping_getQuestions_returns_200Ok_returns_correctAmountOfQuestions() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/home")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                   {
                                       "questions":"3"
                                        ,"category":"General Knowledge"
                                        ,"difficulty":"easy"
                                    }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    @DirtiesContext
    void getMapping_getQuestions_returns_200Ok_returns_QuestionsWithContent() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/home")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                   {
                                       "questions":"3"
                                        ,"category":"General Knowledge"
                                        ,"difficulty":"easy"
                                    }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].description").isNotEmpty())
                .andExpect(jsonPath("$[0].answers[0]").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void postMapping_postAnswers_return200OK_returnsCorrectScoreForWrongAnswers() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/home")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                   {
                                       "questions":"3"
                                        ,"category":"General Knowledge"
                                        ,"difficulty":"easy"
                                    }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/questions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].description").isNotEmpty())
                .andExpect(jsonPath("$[0].answers[0]").isNotEmpty())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<QuestionUnsorted> questions = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<QuestionUnsorted>>() {
        });
        String description1 = questions.get(0).getDescription();
        String description2 = questions.get(1).getDescription();
        String description3 = questions.get(2).getDescription();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/questions")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                   {
                                       "answerObjectList":[
                                           {
                                               "description":"%s"
                                               ,"answer":"falseAnswer"
                                           },
                                           {
                                               "description":"%s"
                                               ,"answer":"falseAnswer"
                                           },
                                           {
                                               "description":"%s"
                                               ,"answer":"falseAnswer"
                                           }
                                       ]
                                    }
                                """.formatted(description1, description2, description3)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Score: 0/3"));
    }

}