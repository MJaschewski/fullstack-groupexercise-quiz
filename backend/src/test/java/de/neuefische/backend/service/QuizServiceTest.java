package de.neuefische.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.CategoryList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {
    QuizService quizService = new QuizService();

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

}