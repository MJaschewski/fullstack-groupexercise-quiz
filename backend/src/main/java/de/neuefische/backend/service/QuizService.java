package de.neuefische.backend.service;

import de.neuefische.backend.model.AnswerDTO;
import de.neuefische.backend.model.CategoryList;
import de.neuefische.backend.model.QuestionUnsorted;
import de.neuefische.backend.model.TriviaApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizService {
    private CategoryList categories;
    private TriviaApiResponse triviaApiResponse;
    private List<QuestionUnsorted> questionUnsortedList;
    private int correctAnswers = 0;
    private int numOfQuestions = 0;

    WebClient webClient = WebClient.create("https://opentdb.com");

    public CategoryList getCategories() {
        this.categories = Objects.requireNonNull(webClient.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(CategoryList.class)
                        .block())
                .getBody();
        return categories;
    }

    public boolean setTriviaApiResponse(String difficulty, String category, String numQuestions) {
        int categoryId = -1;
        if (category != null) {
            getCategories();
        }
        for (int i = 0; i < categories.getTrivia_categories().size(); i++) {
            if (categories.getTrivia_categories().get(i).getName().equals(category)) {
                categoryId = categories.getTrivia_categories().get(i).getId();
            }
        }

        triviaApiResponse = Objects.requireNonNull(webClient.get()
                        .uri("/api.php?amount=" + numQuestions + "&category=" + categoryId + "&difficulty=" + difficulty + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(TriviaApiResponse.class)
                        .block())
                .getBody();
        if (this.triviaApiResponse != null) {
            numOfQuestions = triviaApiResponse.getResults().size();
            return !this.triviaApiResponse.equals(List.of());
        } else {
            return false;
        }
    }

    public List<QuestionUnsorted> getQuestionUnsortedList() {
        ShuffleService shuffleService = new ShuffleService();
        List<QuestionUnsorted> newQuestionUnsortedList = new ArrayList<>();
        for (int i = 0; i < triviaApiResponse.getResults().size(); i++) {
            QuestionUnsorted nextQuestion = new QuestionUnsorted();
            nextQuestion.setDescription(triviaApiResponse.getResults().get(i).getQuestion());

            List<String> unsortedAnswers = new ArrayList<>();
            unsortedAnswers.add(triviaApiResponse.getResults().get(i).getCorrect_answer());
            for (int j = 0; j < 3; j++) {
                unsortedAnswers.add(triviaApiResponse.getResults().get(i).getIncorrect_answers().get(j));
            }
            unsortedAnswers = shuffleService.shuffleList(unsortedAnswers);
            nextQuestion.setAnswers(unsortedAnswers);

            newQuestionUnsortedList.add(nextQuestion);
        }
        this.questionUnsortedList = newQuestionUnsortedList;
        return questionUnsortedList;
    }

    public String postAnswers(AnswerDTO answerDTO) {
        correctAnswers = 0;
        String result = "Score: ";
        int numOfAnswers = answerDTO.getAnswerObjectList().size();
        for (int i = 0; i < numOfAnswers; i++) {
            for (int j = 0; j < numOfQuestions; j++) {
                if (Objects.equals(answerDTO.getAnswerObjectList().get(i).getDescription(), triviaApiResponse.getResults().get(j).getQuestion())
                        && Objects.equals(answerDTO.getAnswerObjectList().get(i).getAnswer(), triviaApiResponse.getResults().get(j).getCorrect_answer())) {
                    correctAnswers++;
                }
            }
        }
        return result + " " + correctAnswers + "/" + numOfQuestions;
    }
}
