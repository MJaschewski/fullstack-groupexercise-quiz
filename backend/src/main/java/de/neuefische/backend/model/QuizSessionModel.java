package de.neuefische.backend.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class QuizSessionModel {
    private String sessionId;
    private List<QuestionModel> questionList;
    private int currentQuestionIndex;
    private boolean done;
    int score;

    public QuestionModel getCurrentQuestion() {
        return questionList.get(currentQuestionIndex);
    }

    public QuizSessionModel()
    {
        sessionId = UUID.randomUUID().toString();
        questionList = new ArrayList<>();
    }

    public void  addQuestion(QuestionModel question) {
        questionList.add(question);
    }

    public QuestionModel getQuestion(String questionID) {
        for (QuestionModel question : questionList)
        {
            if (question.getQuestionID().equals(questionID)) {
                return question;
            }
        }
        return null;
    }

    public boolean incrementCurrentQuestionIndex() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
            return true;
        }
        else {
            done = true;
            return false;
        }
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questionList.size()-1;
    }

    public Integer incrementScore()
    {
        return ++score;
    }

    public boolean getResult(String questionID) throws Exception {
        for (QuestionModel question : questionList) {
            if (question.getQuestionID().equals(questionID)) {
                return question.isCorrect();
            }
        }
        throw new Exception("Question not found");
    }
}
