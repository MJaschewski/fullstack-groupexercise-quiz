package de.neuefische.backend.service;

import de.neuefische.backend.model.OpentdbModel;
import de.neuefische.backend.model.QuizCategory;
import de.neuefische.backend.model.QuizDifficulty;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class QuizService {
    public void CreateQuizSession(QuizDifficulty difficulty, QuizCategory category, int questions)
    {
        WebClient client = WebClient.create("https://opentdb.com");
        Objects.requireNonNull(client.get()
                        .uri("/api.php?amount=" + questions + "&category=" + category.toString() + "&difficulty=" + difficulty.toString() + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(OpentdbModel[].class)
                        .block())
                .getBody();
    }
}
