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
    private List<QuestionApi> questionList;
    private int currentQuestionIndex;
    private QuestionApi[] questionRepo;
    private Boolean[] scoreRepo;
}
