package de.neuefische.backend.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class QuestionModel {
    private String questionID;
    private Integer number;
    private String category;
    private String type;
    private String question;
    private String difficulty;
    private int correctAnswer;
    private List<String> answers;
    private int userAnswer;

    public QuestionModel(Integer number) {
        this.questionID = UUID.randomUUID().toString();
        this.answers = new ArrayList<>();
        this.number = number;
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public void setAnswer(int answer) {
        this.userAnswer = answer;
    }

    public boolean isCorrect() {
        return this.correctAnswer == this.userAnswer;
    }
}
