package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationQuestion {
    private String description;
    private String difficulty;
    private String givenAnswer;
    private String correctAnswer;
}
