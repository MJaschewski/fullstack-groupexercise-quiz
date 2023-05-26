package de.neuefische.backend.controller;

import de.neuefische.backend.model.AnswerDTO;
import de.neuefische.backend.model.CategoryList;
import de.neuefische.backend.model.QuestionUnsorted;
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

    @PostMapping("/home")
    public boolean postQuizSession(@RequestBody QuizRequest quizRequest) {
        return service.setTriviaApiResponse(quizRequest.getDifficulty(), quizRequest.getCategory(), quizRequest.getQuestions());
    }

    @GetMapping("/questions")
    public List<QuestionUnsorted> getQuestions() {
        return service.getQuestionUnsortedList();
    }

    @PostMapping("/questions")
    public String postAnswers(@RequestBody AnswerDTO answerDTO) {
        return service.postAnswers(answerDTO);
    }

}
