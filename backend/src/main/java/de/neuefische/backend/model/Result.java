package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Highscore")
public class Result {
    @Id
    private String id;
    private String playerName;
    private int score;
    private String difficulty;
    private String category;
    private int numOfQuestions;
}
