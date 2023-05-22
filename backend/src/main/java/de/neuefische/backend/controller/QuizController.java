package de.neuefische.backend.controller;

import de.neuefische.backend.model.AnswerModel;
import de.neuefische.backend.model.QuestionModel;
import de.neuefische.backend.model.QuizParameterModel;
import de.neuefische.backend.model.trivia.TriviaObject;
import de.neuefische.backend.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class QuizController {
    QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }
    @PostMapping(path="/home")
    public String PostHome(@RequestBody QuizParameterModel quizParameter) {
        return service.createQuizSession(quizParameter);
    }

    @GetMapping("/categories")
    public TriviaObject getCategories() {
        return service.getCategories();
    }

    @GetMapping("/quiz")
    public QuestionModel getQuestion(@RequestParam String sessionID) {
        return service.getQuestion(sessionID);
    }

    @PostMapping("/quiz")
    public void postAnswer(@RequestBody AnswerModel answerModel) {
        service.setAnswer(answerModel);
    }

    @GetMapping("/quiz/{questionID}")
    public boolean getResult(@RequestParam String sessionID,  @PathVariable String questionID) {
        try {
            return service.getResult(sessionID, questionID);
        }
        catch (Exception e) {
            // return error code
        }
        return false;
    }

    @GetMapping("/score")
    public Integer getScore(@RequestParam String sessionID) {
        return service.getScore(sessionID);
    }
}
