package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    WebClient client = WebClient.create("https://opentdb.com");


    public TriviaObject getCategories() {

        return Objects.requireNonNull(client.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(TriviaObject.class)
                        .block())
                .getBody();
    }

    public List<QuizSession> getQuizSession(String difficulty, int category, Integer numQuestions) {
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
