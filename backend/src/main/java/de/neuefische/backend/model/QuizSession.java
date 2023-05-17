package de.neuefische.backend.model;

import de.neuefische.backend.model.Question;
import de.neuefische.backend.model.QuizParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizSession {
    private String sessionId;
    private List<Question> questionList;
    private int currentQuestionIndex;
    private Question[] questionRepo;
    private Boolean[] scoreRepo;



    public Question getCurrentQuestion() {
        return questionList.get(currentQuestionIndex);
    }


}
