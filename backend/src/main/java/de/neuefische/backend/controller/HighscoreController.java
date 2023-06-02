package de.neuefische.backend.controller;

import de.neuefische.backend.service.HighscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/highscores")
public class HighscoreController {

    @Autowired
    private HighscoreService highscoreService;

    @GetMapping
    public List<Highscore> getHighscores() {
        return highscoreService.getHighscores();
    }

    @PostMapping
    public void addHighscore(@RequestBody Highscore highscore) {
        highscoreService.addHighscore(highscore);
    }
}
