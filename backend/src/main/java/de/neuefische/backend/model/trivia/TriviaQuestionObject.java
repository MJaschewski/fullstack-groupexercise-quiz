package de.neuefische.backend.model.trivia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TriviaQuestionObject {
    public Integer response_code;
    public List<TriviaQuestion> results;
    public TriviaQuestionObject(List<TriviaQuestion> triviaQuestions) {
        this.results = triviaQuestions;
    }
}
