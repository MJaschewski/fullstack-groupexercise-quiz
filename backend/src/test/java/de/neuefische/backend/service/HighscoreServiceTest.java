package de.neuefische.backend.service;

import de.neuefische.backend.model.Result;
import de.neuefische.backend.model.ResultDTO;
import de.neuefische.backend.repo.HighscoreRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class HighscoreServiceTest {

    UUIDService uuidService = mock(UUIDService.class);
    HighscoreRepo highscoreRepo = mock(HighscoreRepo.class);

    HighscoreService highscoreService = new HighscoreService(highscoreRepo, uuidService);

    @Test
    void getResults_() {
        //Given
        Result result = new Result();
        List<Result> expected = List.of(result);
        when(highscoreRepo.findAll()).thenReturn(expected);
        //When
        List<Result> actual = highscoreService.getResults();
        //Then
        verify(highscoreRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void addResults_returns_addedResult() {
        //Given
        ResultDTO newResult = new ResultDTO("testPlayer", 10, "easy", "test", 10);
        String id = "1";
        when(uuidService.generateUUID()).thenReturn(id);
        Result expected = new Result(id, newResult.getPlayerName(), newResult.getScore(), newResult.getDifficulty(), newResult.getCategory(), newResult.getNumOfQuestions());
        when(highscoreRepo.save(expected)).thenReturn(expected);
        //When
        Result actual = highscoreService.addResult(newResult);
        //Then
        verify(uuidService).generateUUID();
        verify(highscoreRepo).save(expected);
        assertEquals(expected, actual);
    }
}