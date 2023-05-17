package de.neuefische.backend.model;

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

    WebClient webClient = WebClient.create();

    public QuizSession(QuizParameter quizParameter){
        this.quizParameter = quizParameter;

    }

    /*
    private Question[] getQuestionsTriviaAPI(QuizParameter quizParameter){
        //Question[] response =
    }

     */


}
