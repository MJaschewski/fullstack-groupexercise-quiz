package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private String playerName;
    private int score;
    private String difficulty;
    private String category;
    private int numOfQuestions;
}
