package de.neuefische.backend.service;

import de.neuefische.backend.model.TriviaObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class QuizService {
    private TriviaObject categories;

    WebClient client = WebClient.create("https://opentdb.com");

    public TriviaObject getCategories() {
        categories = Objects.requireNonNull(client.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(TriviaObject.class)
                        .block())
                .getBody();
        return categories;
    }
}
