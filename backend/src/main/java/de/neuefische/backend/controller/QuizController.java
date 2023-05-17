package de.neuefische.backend.controller;

import de.neuefische.backend.model.QuizParameter;
import de.neuefische.backend.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }
    @PostMapping("/home")
    public void PostHome(@RequestBody QuizParameter quizParameter) {
        service.CreateQuizSession(quizParameter.getDifficulty(), quizParameter.getCategory(), quizParameter.getNumQuestions());
    }
}
