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

    public int getScore() {
        int score = 0;
        for (QuestionModel question : questionList) {
            if (question.getCorrectAnswer() == question.getUserAnswer()) {
                score++;
            }
        }
        return score;
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

    public Integer getResult(String questionID)
    {
        // TODO: correct answer only if answered else -1
        return getQuestion(questionID).getCorrectAnswer();
    }
}
