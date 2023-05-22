package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizParameterModel {
    private String difficulty;
    private Integer category;
    private Integer numQuestions;
}
