package de.neuefische.backend.controller;

import de.neuefische.backend.model.QuizCategory;
import de.neuefische.backend.model.QuizDifficulty;
import de.neuefische.backend.service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuizController {

    QuizService service;

    public QuizController(QuizService service) {
        super();
        this.service = service;

    }
    @PostMapping("/home")
    public void PostHome(QuizDifficulty difficulty, QuizCategory category, int numberQuestions) {
        service.CreateQuizSession(difficulty, category, numberQuestions);
    }

}
