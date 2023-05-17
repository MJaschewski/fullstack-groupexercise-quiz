package de.neuefische.backend.controller;

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
    public void PostHome(String difficulty, String genre, String type, int questions) {
        service.CreateQuizSession(difficulty, genre, type, questions);
    }

}
