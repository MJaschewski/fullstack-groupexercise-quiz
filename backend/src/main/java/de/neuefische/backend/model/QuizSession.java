package de.neuefische.backend.model;

import de.neuefische.backend.model.Question;
import de.neuefische.backend.model.QuizParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


public class QuizSession {
    private String sessionId;
    private List<Question> questionList;
    private int currentQuestionIndex;
    private Question[] questionRepo;
    private Boolean[] scoreRepo;

    public QuizSession(String sessionId, List<Question> questionList, int currentQuestionIndex, Question[] questionRepo, Boolean[] scoreRepo) {
        this.sessionId = sessionId;
        this.questionList = questionList;
        this.currentQuestionIndex = currentQuestionIndex;
        this.questionRepo = questionRepo;
        this.scoreRepo = scoreRepo;
    }

    public Question getCurrentQuestion() {
        return questionList.get(currentQuestionIndex);
    }


}
