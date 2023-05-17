package de.neuefische.backend.service;

import de.neuefische.backend.model.Question;
import de.neuefische.backend.model.QuizParameter;

public class QuizSession {
    private String sessionId;
    private Question[] questionRepo;
    private Boolean[] scoreRepo;
    QuizParameter quizParameter;

    public QuizSession(QuizParameter quizParameter){
        this.quizParameter = quizParameter;
    }

}
