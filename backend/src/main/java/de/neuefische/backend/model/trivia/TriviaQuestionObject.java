package de.neuefische.backend.model.trivia;

import lombok.Data;

import java.util.List;

@Data
public class TriviaQuestionObject {
    public Integer response_code;
    public List<TriviaQuestion> triviaQuestions;
}
