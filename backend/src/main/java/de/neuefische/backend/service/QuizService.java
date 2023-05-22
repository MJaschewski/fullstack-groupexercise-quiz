package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import de.neuefische.backend.model.trivia.TriviaObject;
import de.neuefische.backend.model.trivia.TriviaQuestionObject;
import de.neuefische.backend.repository.SessionRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class QuizService {
    SessionRepo sessionRepo;
    public QuizService(SessionRepo repo)
    {
        sessionRepo = repo;
    }

    public TriviaObject getCategories() {
        WebClient client = WebClient.create("https://opentdb.com");
        return Objects.requireNonNull(client.get()
                        .uri("/api_category.php")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(TriviaObject.class)
                        .block())
                .getBody();
    }

    public TriviaQuestionObject getQuizData(String difficulty, Integer category, Integer numQuestions) {
        WebClient client = WebClient.create("https://opentdb.com");
        return Objects.requireNonNull(client.get()
                        .uri("/api.php?amount=" + numQuestions + "&category=" + category + "&difficulty=" + difficulty + "&type=multiple")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(TriviaQuestionObject.class)
                        .block())
                .getBody();
    }

    public String createQuizSession(QuizParameterModel quizParameter)
    {
        var quizData = getQuizData(quizParameter.getDifficulty(), quizParameter.getCategory(), quizParameter.getNumQuestions());

        var newSession = new QuizSessionModel();
        for (var newQuestion: (quizData.triviaQuestions)) {
            var quizQuestion = new QuestionModel();
            quizQuestion.setCategory(newQuestion.getCategory());
            quizQuestion.setType(newQuestion.getType());
            quizQuestion.setQuestion(newQuestion.getQuestion());
            quizQuestion.setDifficulty(newQuestion.getDifficulty());
            quizQuestion.setQuestion(newQuestion.getQuestion());

            quizQuestion.setCorrectAnswer(0);
            quizQuestion.addAnswer(newQuestion.getCorrect_answer());

            for(var newAnswer: newQuestion.getIncorrect_answers())
            {
                quizQuestion.addAnswer(newAnswer);
            }
            newSession.addQuestion(quizQuestion);
        }
        sessionRepo.addSession(newSession);
        return newSession.getSessionId();
    }

    public QuestionModel getQuestion(String sessionId) {
        var session = sessionRepo.getSession(sessionId);
        if (session.hasNextQuestion()) {
            session.incrementCurrentQuestionIndex();
        }
        else
        {
            return null;
        }
        return session.getCurrentQuestion();
    }

    public void setAnswer(AnswerModel answerModel) {
        var session = sessionRepo.getSession(answerModel.getSessionID());
        var question = session.getCurrentQuestion();
        question.setAnswer(answerModel.getAnswer());
    }

    public boolean getResult(String sessionID, String questionID) throws Exception {
        var session = sessionRepo.getSession(sessionID);
        return session.getResult(questionID);
    }

    public Integer getScore(String sessionID)
    {
        var session = sessionRepo.getSession(sessionID);
        return session.getScore();
    }
}
