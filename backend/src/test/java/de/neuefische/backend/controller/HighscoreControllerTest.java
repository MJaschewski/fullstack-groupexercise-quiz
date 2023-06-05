package de.neuefische.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HighscoreControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void postMapping_addResult_return_200Ok_return_string_true() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/highscore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "playerName":"testplayer"
                                ,"score":10
                                ,"difficulty":"easy"
                                ,"category":"test"
                                ,"numOfQuestions":10
                                }"""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                "playerName":"testplayer"
                                ,"score":10
                                ,"difficulty":"easy"
                                ,"category":"test"
                                ,"numOfQuestions":10
                                }"""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void getMapping_getResults_return_200Ok_return_ListResults() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/highscore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "playerName":"testplayer1"
                                ,"score":10
                                ,"difficulty":"easy"
                                ,"category":"test1"
                                ,"numOfQuestions":10
                                }"""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                "playerName":"testplayer1"
                                ,"score":10
                                ,"difficulty":"easy"
                                ,"category":"test1"
                                ,"numOfQuestions":10
                                }"""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

        mvc.perform(MockMvcRequestBuilders.post("/api/highscore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "playerName":"testplayer2"
                                ,"score":10
                                ,"difficulty":"easy"
                                ,"category":"test2"
                                ,"numOfQuestions":10
                                }"""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                "playerName":"testplayer2"
                                ,"score":10
                                ,"difficulty":"easy"
                                ,"category":"test2"
                                ,"numOfQuestions":10
                                }"""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

        mvc.perform(MockMvcRequestBuilders.get("/api/highscore"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                    {
                                    "playerName":"testplayer"
                                    ,"score":10
                                    ,"difficulty":"easy"
                                    ,"category":"test"
                                    ,"numOfQuestions":10
                                    },
                                    {
                                    "playerName":"testplayer1"
                                    ,"score":10
                                    ,"difficulty":"easy"
                                    ,"category":"test1"
                                    ,"numOfQuestions":10
                                    },
                                    {
                                    "playerName":"testplayer2"
                                    ,"score":10
                                    ,"difficulty":"easy"
                                    ,"category":"test2"
                                    ,"numOfQuestions":10
                                }]"""))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNotEmpty());
    }
}