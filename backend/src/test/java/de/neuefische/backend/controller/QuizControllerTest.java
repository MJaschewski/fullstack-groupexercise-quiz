package de.neuefische.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import de.neuefische.backend.model.QuizParameter;
import de.neuefische.backend.model.TriviaCategories;
import de.neuefische.backend.model.TriviaObject;
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

    @Test
    void postHomeShouldCallGetQuizSession() {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("difficulty", "easy");
        paramMap.add("category", "1");
        paramMap.add("numQuestions", "10");

        QuizParameter expectedParameter = new QuizParameter("easy", "1", 10);

        quizController.postHome(paramMap);

        verify(quizService).getQuizSession(
                expectedParameter.getDifficulty(),
                Integer.parseInt(expectedParameter.getCategory()),
                expectedParameter.getNumQuestions()
        );
    }

    @Test
    void getCategoriesShouldReturnListOfTriviaObjects() {

        var expectedCategories = new TriviaObject(new LinkedList<TriviaCategories>());

        expectedCategories.add(new TriviaCategories());
        expectedCategories.add(new TriviaCategories());

        when(quizService.getCategories()).thenReturn(expectedCategories);

        TriviaObject actualCategories = quizController.getCategories();

        verify(quizService, times(1)).getCategories();
        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    void getQuizSessionsShouldCallGetQuizSession() {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("difficulty", "hard");
        paramMap.add("category", "2");
        paramMap.add("numQuestions", "5");

        QuizParameter expectedParameter = new QuizParameter("hard", "2", 5);

        quizController.getQuizSessions(paramMap);

        verify(quizService, times(1)).getQuizSession(
                expectedParameter.getDifficulty(),
                Integer.parseInt(expectedParameter.getCategory()),
                expectedParameter.getNumQuestions()
        );
    }
}
