package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizSession {
    private String sessionId;
    private String questionId;
    private List<Question> questionList;
    private int currentQuestionIndex;
    private Question[] questionRepo;
    private Boolean[] scoreRepo;
}
