package de.neuefische.backend.controller;

import de.neuefische.backend.model.QuizParameter;
import de.neuefische.backend.service.QuizService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }
    @PostMapping(path="/home",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public void PostHome(@RequestParam MultiValueMap<String,String> paramMap) {
        QuizParameter quizParameter = new QuizParameter(paramMap.getFirst("difficulty"), paramMap.getFirst("category"), Integer.parseInt((paramMap.getFirst("numQuestions") == null)?"0":paramMap.getFirst("numQuestions")));
        // QuizParameter quizParameter
        service.CreateQuizSession(quizParameter.getDifficulty(), quizParameter.getCategory(), quizParameter.getNumQuestions());
    }
}
