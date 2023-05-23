package de.neuefische.backend.controller;

import de.neuefische.backend.model.AnswerModel;
import de.neuefische.backend.model.QuestionModel;
import de.neuefische.backend.model.QuizParameterModel;
import de.neuefische.backend.model.rest.IDs;
import de.neuefische.backend.model.rest.SessionID;
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
    public String postHome(@RequestBody QuizParameterModel quizParameter) {
        return service.createQuizSession(quizParameter);
    }

    @GetMapping("/categories")
    public TriviaObject getCategories() {
        return service.getCategories();
    }

    @GetMapping("/quiz")
    public QuestionModel getQuestion(@RequestBody SessionID sessionID){
        return service.getQuestion(sessionID.getSessionID());
    }

    @PostMapping("/quiz")
    public void postAnswer(@RequestBody AnswerModel answerModel) {
        service.setAnswer(answerModel);
    }

    @GetMapping("/result")
    public Integer getResult(@RequestBody IDs ids) {
        return service.getResult(ids.getSessionID(), ids.getQuestionID());
    }

    @GetMapping("/score")
    public Integer getScore(@RequestBody SessionID sessionID) {
        return service.getScore(sessionID.getSessionID());
    }
}
