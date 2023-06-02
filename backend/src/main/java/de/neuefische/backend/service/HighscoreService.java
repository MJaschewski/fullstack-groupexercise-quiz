package de.neuefische.backend.service;

import de.neuefische.backend.repo.HighscoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HighscoreService {

    private final HighscoreRepo highscoreRepo;


    public List<Highscore> getHighscores() {
    }

    public String addHighscore(Highscore highscore) {
        return null;
    }
}
