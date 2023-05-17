package de.neuefische.backend.service;

import de.neuefische.backend.model.Question;
import de.neuefische.backend.model.QuizParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

public class QuizSession {
    private String sessionId;
    private Question[] questionRepo;
    private Boolean[] scoreRepo;
    QuizParameter quizParameter;

    public QuizSession(QuizParameter quizParameter){
        this.quizParameter = quizParameter;

        WebClient webClient = WebClient.create();
    }

}
