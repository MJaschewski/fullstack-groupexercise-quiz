package de.neuefische.backend.repository;

import de.neuefische.backend.model.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionRepoTest {

    @Test
    void when_addSession_returns_addedSession() {
        //Given
            SessionRepo sessionRepo = new SessionRepo();
            List<String> wrongAnswers = new ArrayList<>();
            wrongAnswers.add("testWrong1");
            wrongAnswers.add("testWrong2");
            wrongAnswers.add("testWrong3");
            Question questionToAdd = new Question("testCategory","testType","testDifficulty","testQuestion","testRight",wrongAnswers);
            List<Question> sessionToAdd = new ArrayList<>();
            sessionToAdd.add(questionToAdd);

        //When
        List<Question> actual = sessionRepo.addSession("testID", sessionToAdd);

        //Then
        assertEquals(sessionToAdd,actual);
    }
}