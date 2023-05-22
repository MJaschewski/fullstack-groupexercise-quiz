package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuestionModel {
    private String questionID;
    private String category;
    private String type;
    private String question;
    private String difficulty;
    private int correctAnswer;
    private List<String> answers;
    private int userAnswer;

    public QuestionModel() {
        questionID = UUID.randomUUID().toString();
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public void setAnswer(int answer) {
        userAnswer = answer;
    }

    public boolean isCorrect() {
        return correctAnswer == userAnswer;
    }
}
