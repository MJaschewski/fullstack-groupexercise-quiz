package de.neuefische.backend.controller;

import de.neuefische.backend.model.QuizParameter;
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
    public void PostHome(@RequestParam String questions, @RequestParam String difficulty, @RequestParam String category) {
        service.getQuizSession(difficulty, Integer.parseInt(category), Integer.parseInt(questions));
    }

    @GetMapping("/categories")
    public TriviaObject getCategories() {
        return service.getCategories();
    }

    @GetMapping("/quiz")
    public void getQuizSessions(@RequestParam MultiValueMap<String,String> paramMap) {
        QuizParameter quizParameter = new QuizParameter(paramMap.getFirst("difficulty"), paramMap.getFirst("category"), Integer.parseInt((paramMap.getFirst("numQuestions") == null)?"0":paramMap.getFirst("numQuestions")));
        service.getQuizSession(quizParameter.getDifficulty(), Integer.parseInt(quizParameter.getCategory()), quizParameter.getNumQuestions());
    }
}
