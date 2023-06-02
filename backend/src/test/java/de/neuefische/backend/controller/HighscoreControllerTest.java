package de.neuefische.backend.controller;

import de.neuefische.backend.model.ResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HighscoreControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void post_Mapping_addResult_return_200Ok_return_string_true() {
        //Given
        ResultDTO resultDTO = new ResultDTO();
    }
}