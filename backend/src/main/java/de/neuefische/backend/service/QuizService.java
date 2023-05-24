package de.neuefische.backend.service;

import de.neuefische.backend.model.CategoryList;
import de.neuefische.backend.model.Question;
import de.neuefische.backend.model.TriviaApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {
    private CategoryList categories;

    WebClient webClient = WebClient.create("https://opentdb.com");

    public CategoryList getCategories() {
        this.categories = Objects.requireNonNull(webClient.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(CategoryList.class)
                        .block())
                .getBody();
        return categories;
    }

    public TriviaApiResponse getQuizSession(String difficulty, String category, String numQuestions) {
        int questionNum = Integer.parseInt(numQuestions);
        int categoryId = -1;
        if (category != null) {
            getCategories();
        }
        for (int i = 0; i < categories.getTrivia_categories().size(); i++) {
            if (categories.getTrivia_categories().get(i).getName().equals(category)) {
                categoryId = categories.getTrivia_categories().get(i).getId();
            }
        }
        WebClient client = WebClient.create("https://opentdb.com");
        return Objects.requireNonNull(client.get()
                        .uri("/api.php?amount=" + questionNum + "&category=" + categoryId + "&difficulty=" + difficulty + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(TriviaApiResponse.class)
                        .block())
                .getBody();

    }
}
