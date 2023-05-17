package de.neuefische.backend.repository;

import de.neuefische.backend.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionRepo {

    private Map<String, Question> sessionMap = new HashMap<>();

    public List<Question> getQuestions() {
        return new ArrayList<>(sessionMap.values());

    }


}
