package de.neuefische.backend.repository;

import de.neuefische.backend.model.QuizSessionModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionRepo {
    private final Map<String, QuizSessionModel> sessions = new HashMap<>();

    public void addSession(QuizSessionModel quizSessions) {
            sessions.put(quizSessions.getSessionId(), quizSessions);
    }
    public QuizSessionModel getSession(String sessionId) {
        return sessions.get(sessionId);
    }
}
