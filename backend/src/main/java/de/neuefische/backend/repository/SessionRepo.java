package de.neuefische.backend.repository;

import de.neuefische.backend.model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SessionRepo {

    private Map<String, List<Question>> sessionMap = new HashMap<>();

    public List<Question> getAllQuestions() {
        List<Question> allQuestions = new ArrayList<>();
        for (List<Question> questions : sessionMap.values()) {
            allQuestions.addAll(questions);
        }
        return allQuestions;
    }


    public void addSession(String sessionId, List<Question> questions) {
        sessionMap.put(sessionId, questions);
    }


}
