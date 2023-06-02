package de.neuefische.backend.controller;

import de.neuefische.backend.model.Result;
import de.neuefische.backend.model.ResultDTO;
import de.neuefische.backend.service.HighscoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/highscore")
@RequiredArgsConstructor
public class HighscoreController {

    private final HighscoreService highscoreService;

    @GetMapping
    public List<Result> getResults() {
        return highscoreService.getResults();
    }

    @PostMapping
    public String addResult(@RequestBody ResultDTO resultDTO) {
        return highscoreService.addResult(resultDTO);
    }

}
