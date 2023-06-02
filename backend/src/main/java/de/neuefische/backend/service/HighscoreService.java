package de.neuefische.backend.service;

import de.neuefische.backend.model.Result;
import de.neuefische.backend.model.ResultDTO;
import de.neuefische.backend.repo.HighscoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HighscoreService {

    private final HighscoreRepo highscoreRepo;

    private final UUIDService uuidService;

    public List<Result> getResults() {
        return highscoreRepo.findAll();
    }

    public String addResult(ResultDTO resultDTO) {
        Result highscore = new Result(uuidService.generateUUID(), resultDTO.getPlayerName(), resultDTO.getScore(), resultDTO.getDifficulty(), resultDTO.getCategory(), resultDTO.getNumOfQuestions());
        highscoreRepo.save(highscore);
        return Boolean.toString(highscoreRepo.existsById(highscore.getId()));
    }
}
