package de.neuefische.backend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriviaApiResponseTest {

    @Test
    void constructor_TriviaApiResponse() throws JsonProcessingException {
        //Given
        List<String> incorrect = new ArrayList<>();
        incorrect.add("Brinjal");
        incorrect.add("Guinea Squash");
        incorrect.add("Melongene");
        Question q1 = new Question("General Knowledge"
                , "multiple"
                , "hard"
                ,"Which of the following is not another name for the eggplant?"
                , "Potimarron"
                ,incorrect);
        List<Question> questionList = new ArrayList<>();
        questionList.add(q1);
        questionList.add(q1);
        TriviaApiResponse triviaApiResponse = new TriviaApiResponse(0,questionList);
        String expected = """
                {"response_code":0,"results":[{"category":"General Knowledge","type":"multiple","difficulty":"hard","question":"Which of the following is not another name for the eggplant?","correct_answer":"Potimarron","incorrect_answers":["Brinjal","Guinea Squash","Melongene"]},{"category":"General Knowledge","type":"multiple","difficulty":"hard","question":"Which of the following is not another name for the eggplant?","correct_answer":"Potimarron","incorrect_answers":["Brinjal","Guinea Squash","Melongene"]}]}
                """;
        //When
        ObjectMapper objectMapper = new ObjectMapper();
        String actual = objectMapper.writeValueAsString(triviaApiResponse);
        //Then
        assertTrue(expected.contains(actual));

    }

}