package de.neuefische.backend.controller;

import de.neuefische.backend.model.Question;
import de.neuefische.backend.model.QuizParameter;
import de.neuefische.backend.model.QuizRequest;
import de.neuefische.backend.model.TriviaObject;
import de.neuefische.backend.service.QuizService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class QuizController {

    QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }
    @PostMapping(path="/home")
    public List<Question> PostHome(@RequestBody QuizRequest quizRequest) {
        return service.getQuizSession(quizRequest.getDifficulty(), quizRequest.getCategory(), quizRequest.getQuestions());
    }

    @GetMapping("/categories")
    public TriviaObject getCategories() {
        return service.getCategories();
    }

}
