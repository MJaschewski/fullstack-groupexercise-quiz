package de.neuefische.backend.service;

import de.neuefische.backend.model.Result;
import de.neuefische.backend.repo.HighscoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HighscoreService {

    private final HighscoreRepo highscoreRepo;


    public List<Result> getResults() {
        return null;
    }

    public String addResult(Result highscore) {
        return null;
    }
}
