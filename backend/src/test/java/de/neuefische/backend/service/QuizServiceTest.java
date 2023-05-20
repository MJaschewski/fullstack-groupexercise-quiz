package de.neuefische.backend.service;
import de.neuefische.backend.model.QuizSession;
import de.neuefische.backend.model.TriviaObject;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizServiceTest {

    private MockWebServer mockWebServer;
    private QuizService quizService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        quizService = new QuizService(webClient);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void getCategoriesShouldReturnTriviaObject() {
        // Arrange
        String jsonResponse = "{\"trivia_categories\": [{\"id\": 9,\"name\": \"General Knowledge\"}]}";
        mockWebServer.enqueue(new MockResponse()
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(jsonResponse));

        // Act
        TriviaObject triviaObject = quizService.getCategories();

        // Assert
        assertEquals(1, triviaObject.getTriviaCategories().size());
        assertEquals(9, triviaObject.getTriviaCategories().get(0).getId());
        assertEquals("General Knowledge", triviaObject.getTriviaCategories().get(0).getName());
    }
}


