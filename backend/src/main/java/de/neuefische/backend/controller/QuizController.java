package de.neuefische.backend.controller;

import de.neuefische.backend.model.CategoryList;
import de.neuefische.backend.model.QuestionApi;
import de.neuefische.backend.model.QuizRequest;
import de.neuefische.backend.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService service;

    @GetMapping("/categories")
    public CategoryList getCategories() {
        return service.getCategories();
    }

    @PostMapping(path="/home")
    public List<QuestionApi> postHome(@RequestBody QuizRequest quizRequest) {
        return service.getQuizSession(quizRequest.getDifficulty(), quizRequest.getCategory(), quizRequest.getQuestions()).getResults();
    }

}
