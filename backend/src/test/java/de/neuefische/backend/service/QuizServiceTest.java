package de.neuefische.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class QuizServiceTest {


    ShuffleService mockShuffleService = mock(ShuffleService.class);
    QuizService quizService = new QuizService(mockShuffleService);

    @Autowired
    WebTestClient webTestClient;


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
    @DirtiesContext
    void setTriviaApiResponse_returnTrueIfApiResponseIsSet() throws JsonProcessingException {
        CategoryObject categoryObject = new CategoryObject(1,"General Knowledge");
        List<CategoryObject> categoryObjectList = new ArrayList<>();
        categoryObjectList.add(categoryObject);

        TriviaApiResponse triviaApiResponse = new TriviaApiResponse();
        triviaApiResponse.setResults(new ArrayList<>());
        triviaApiResponse.getResults().add(new QuestionApi("Entertainment: Video Games","multiple","easy","Which of these is NOT a game under the Worms series?","Worms: Ultimate Mayhem", Arrays.asList("Worms: Reloaded","Worms: Revolution","Worms: Battle Islands")));

        this.webTestClient = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(15)).baseUrl("https://opentdb.com").build();
        webTestClient.get()
                .uri("/api_category.php")
                .accept(MediaType.APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CategoryList.class)
                .consumeWith(response -> response.getResponseBody().equals(categoryObjectList));

        webTestClient.get()
                .uri("/api.php?amount=1&category=1&difficulty=easy&type=multiple")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TriviaApiResponse.class)
                .consumeWith(response -> response.getResponseBody().equals(triviaApiResponse));

        boolean result = quizService.setTriviaApiResponse("easy","1","1");
        assertTrue(result);
    }



    @Test
    @DirtiesContext
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

    @Test
    @DirtiesContext
    void postAnswers_returnResult() {
        //Given
        AnswerDTO answerDTO = new AnswerDTO();
        TriviaApiResponse triviaApiResponse = new TriviaApiResponse();
        List<QuestionUnsorted> results = new ArrayList<>();

        QuestionApi questionApi1 = new QuestionApi();
        questionApi1.setQuestion("What is the capital of France");
        questionApi1.setCorrect_answer("Paris");
        questionApi1.setIncorrect_answers(List.of("London","Berlin","Madrid"));

        QuestionApi questionApi2 = new QuestionApi();
        questionApi2.setQuestion("What is the largest planet in the solar system?");
        questionApi2.setCorrect_answer("Jupiter");
        questionApi2.setIncorrect_answers(List.of("Saturn","Earth","Mars"));

        AnswerObject answerObject1 = new AnswerObject();
        answerObject1.setDescription("What is the capital of France");
        answerObject1.setAnswer("Paris");

        AnswerObject answerObject2 = new AnswerObject();
        answerObject2.setDescription("What is the largest planet in the solar system?");
        answerObject2.setAnswer("Mars");

        triviaApiResponse.setResults(List.of(questionApi1,questionApi2));
        quizService.setTriviaApiResponse(triviaApiResponse);
        quizService.setNumOfQuestions(List.of(questionApi1, questionApi2).size());

        answerDTO.setAnswerObjectList(List.of(answerObject1, answerObject2));

        String expected = "Score: 1/2";
        //When
        String actual = quizService.postAnswers(answerDTO);
        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DirtiesContext
    void when_getEvaluation_return_CorrectEvaluationDTO() {
        //Given
        List<EvaluationQuestion> evaluationQuestionList = new ArrayList<>();
        EvaluationQuestion testQuestionRight = new EvaluationQuestion("TestQ1", "hard", "right", "right");
        EvaluationQuestion testQuestionWrong = new EvaluationQuestion("TestQ2", "easy", "wrong", "right");
        evaluationQuestionList.add(testQuestionRight);
        evaluationQuestionList.add(testQuestionWrong);
        quizService.setEvaluationList(evaluationQuestionList);

        EvaluationDTO expected = new EvaluationDTO(3, evaluationQuestionList);
        //When
        EvaluationDTO actual = quizService.getEvaluation();

        //Then
        assertEquals(expected, actual);

    }
}