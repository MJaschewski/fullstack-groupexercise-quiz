package de.neuefische.backend.model;

import lombok.Data;

@Data
public class OpentdbModel {
    private QuizCategory category;
    private String type;
    private QuizDifficulty difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
}
