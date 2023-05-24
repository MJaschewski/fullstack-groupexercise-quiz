package de.neuefische.backend.service;

import de.neuefische.backend.model.CategoryList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class QuizService {
    private CategoryList categoryList;

    WebClient webClient = WebClient.create("https://opentdb.com");

    public CategoryList getCategories() {
        categoryList = Objects.requireNonNull(webClient.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(CategoryList.class)
                        .block())
                .getBody();
        return categoryList;
    }
}
