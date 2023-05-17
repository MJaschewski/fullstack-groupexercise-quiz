package de.neuefische.backend.service;

import de.neuefische.backend.model.OpentdbModel;
import de.neuefische.backend.model.QuizCategory;
import de.neuefische.backend.model.QuizDifficulty;
import de.neuefische.backend.model.QuizSession;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {
    public void CreateQuizSession(String difficulty, String category, Integer numQuestions)
    {
        WebClient client = WebClient.create("https://opentdb.com");
        Objects.requireNonNull(client.get()
                        .uri("/api.php?amount=" + numQuestions + "&category=" + category + "&difficulty=" + difficulty + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(OpentdbModel.class)
                        .block())
                .getBody();
    }

    public QuizCategory[] getCategories() {
        WebClient client = WebClient.create("https://opentdb.com");
        return Objects.requireNonNull(client.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(QuizCategory[].class)
                        .block())
                .getBody();
    }

    public List<QuizSession> getQuizSessions(String difficulty, int category, Integer numQuestions) {
        WebClient client = WebClient.create("https://opentdb.com");
        return Objects.requireNonNull(client.get()
                        .uri("/api.php?amount=" + numQuestions + "&category=" + category + "&difficulty=" + difficulty + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(List.class)
                        .block())
                .getBody();
    }
}
