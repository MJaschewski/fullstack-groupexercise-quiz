package de.neuefische.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import de.neuefische.backend.model.QuizParameterModel;
import de.neuefische.backend.model.trivia.TriviaCategories;
import de.neuefische.backend.model.trivia.TriviaObject;
import de.neuefische.backend.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

class QuizControllerTest {

    @Mock
    private QuizService quizService;

    private QuizController quizController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quizController = new QuizController(quizService);
    }

//    @Test
//    void getCategoriesShouldReturnListOfTriviaObjects() {
//        var expectedCategories = new TriviaObject(new LinkedList<TriviaCategories>());
//
//        expectedCategories.add(new TriviaCategories());
//        expectedCategories.add(new TriviaCategories());
//
//        when(quizService.getCategories()).thenReturn(expectedCategories);
//
//        TriviaObject actualCategories = quizController.getCategories();
//
//        verify(quizService, times(1)).getCategories();
//        assertEquals(expectedCategories, actualCategories);
//    }

//    @Test
//    void getQuizSessionsShouldCallGetQuizSession() {
//        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
//        paramMap.add("difficulty", "hard");
//        paramMap.add("category", "2");
//        paramMap.add("numQuestions", "5");
//
//        QuizParameterModel expectedParameter = new QuizParameterModel("hard", "2", 5);
//
//        quizController.getQuizSessions(paramMap);
//
//        verify(quizService, times(1)).GetQuizSession(
//                expectedParameter.getDifficulty(),
//                Integer.parseInt(expectedParameter.getCategory()),
//                expectedParameter.getNumQuestions()
//        );
//    }
}
