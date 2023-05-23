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

    public List<Question> getQuizSession(String difficulty, String category, String numQuestions) {
        int questionNum = Integer.parseInt(numQuestions);
        int categoryId = -1;
        if(category != null){
            getCategories();
        }
        for (int i =0; i < categories.getTrivia_categories().size() ;i++){
            if ( categories.getTrivia_categories().get(i).getName().equals(category)){
                categoryId = categories.getTrivia_categories().get(i).getId();
            }
        }
        WebClient client = WebClient.create("https://opentdb.com");
        return Objects.requireNonNull(client.get()
                        .uri("/api.php?amount=" + questionNum + "&category=" + categoryId + "&difficulty=" + difficulty + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(List.class)
                        .block())
                .getBody();

    }

}
