package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Question {
    private String category;
    private String type;
    private String difficulty;
    private String questionDetails;
    private String correctAnswer;
    private List<String> incorrectAnswers;

}
