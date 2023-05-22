package de.neuefische.backend.model.trivia;

import lombok.Data;

@Data
public class TriviaQuestion {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
}
