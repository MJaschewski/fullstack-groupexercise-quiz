package de.neuefische.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.CategoryList;
import de.neuefische.backend.model.QuestionApi;
import de.neuefische.backend.model.QuestionUnsorted;
import de.neuefische.backend.model.TriviaApiResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QuizServiceTest {


    ShuffleService mockShuffleService = mock(ShuffleService.class);
    QuizService quizService = new QuizService(mockShuffleService);


    @Test
    void when_getCategories_return_categoryList() throws JsonProcessingException {
        //Given
        String apiResponse = """ 
                    {
                    "trivia_categories": [
                        {
                            "id": 9,
                            "name": "General Knowledge"
                        },
                        {
                            "id": 10,
                            "name": "Entertainment: Books"
                        },
                        {
                            "id": 11,
                            "name": "Entertainment: Film"
                        },
                        {
                            "id": 12,
                            "name": "Entertainment: Music"
                        },
                        {
                            "id": 13,
                            "name": "Entertainment: Musicals & Theatres"
                        },
                        {
                            "id": 14,
                            "name": "Entertainment: Television"
                        },
                        {
                            "id": 15,
                            "name": "Entertainment: Video Games"
                        },
                        {
                            "id": 16,
                            "name": "Entertainment: Board Games"
                        },
                        {
                            "id": 17,
                            "name": "Science & Nature"
                        },
                        {
                            "id": 18,
                            "name": "Science: Computers"
                        },
                        {
                            "id": 19,
                            "name": "Science: Mathematics"
                        },
                        {
                            "id": 20,
                            "name": "Mythology"
                        },
                        {
                            "id": 21,
                            "name": "Sports"
                        },
                        {
                            "id": 22,
                            "name": "Geography"
                        },
                        {
                            "id": 23,
                            "name": "History"
                        },
                        {
                            "id": 24,
                            "name": "Politics"
                        },
                        {
                            "id": 25,
                            "name": "Art"
                        },
                        {
                            "id": 26,
                            "name": "Celebrities"
                        },
                        {
                            "id": 27,
                            "name": "Animals"
                        },
                        {
                            "id": 28,
                            "name": "Vehicles"
                        },
                        {
                            "id": 29,
                            "name": "Entertainment: Comics"
                        },
                        {
                            "id": 30,
                            "name": "Science: Gadgets"
                        },
                        {
                            "id": 31,
                            "name": "Entertainment: Japanese Anime & Manga"
                        },
                        {
                            "id": 32,
                            "name": "Entertainment: Cartoon & Animations"
                        }
                    ]
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryList expected = objectMapper.readValue(apiResponse, CategoryList.class);

        //When
        CategoryList actual = quizService.getCategories();

        //Then
        assertEquals(expected,actual);

    }

    @Test
    void getQuestionsUnsortedList_returnListOfQuestionsWithUnsortedAnswers() throws JsonProcessingException {
        //Given
        String apiResponse = """
                {
                    "response_code": 0,
                    "results": [
                        {
                            "category": "Entertainment: Video Games",
                            "type": "multiple",
                            "difficulty": "easy",
                            "question": "Which of these is NOT a game under the Worms series?",
                            "correct_answer": "Worms: Ultimate Mayhem",
                            "incorrect_answers": [
                                "Worms: Reloaded",
                                "Worms: Revolution",
                                "Worms: Battle Islands"
                            ]
                        }
                    ]
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        TriviaApiResponse triviaApiResponse = objectMapper.readValue(apiResponse, TriviaApiResponse.class);
        quizService.setTriviaApiResponse(triviaApiResponse);
        List<String> answers1 = new ArrayList<>();
        answers1.add(triviaApiResponse.getResults().get(0).getCorrect_answer());
        answers1.addAll(triviaApiResponse.getResults().get(0).getIncorrect_answers());
        QuestionUnsorted questionUnsorted1 = new QuestionUnsorted(triviaApiResponse.getResults().get(0).getQuestion(), answers1);
        List<QuestionUnsorted> expected = List.of(questionUnsorted1);

        when(mockShuffleService.shuffleList(any())).thenReturn(answers1);

        //When
        List<QuestionUnsorted> actual = quizService.getQuestionUnsortedList();

        //Then
        verify(mockShuffleService).shuffleList(any());
        assertEquals(expected, actual);

    }



}