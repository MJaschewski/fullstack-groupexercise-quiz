import React, { useEffect, useState } from 'react';
import { Question } from "./QuestionType";
import QuestionCard from "./QuestionCard";
import axios from "axios";
import { UserAnswer } from "./UserAnswerType";
import { useNavigate } from "react-router-dom";

type AnswerDTO = {
    answerObjectList: UserAnswer[]
}

const Questions = () => {
    const [questionsUnsortedList, setQuestionsUnsortedList] = useState<Question[]>([]);
    const [currentIndex, setCurrentIndex] = useState<number>(0);
    const [userAnswers, setUsersAnswer] = useState<UserAnswer[]>([]);
    const [submitResponse, setSubmitResponse] = useState<any>(null);
    const [showScore, setShowScore] = useState<boolean>(false);

    const navigate = useNavigate();

    const handleNext = () => {
        setCurrentIndex((prevIndex) => prevIndex + 1);
    }

    const handleClickEvaluation = () => {
        const answerDTO: AnswerDTO = { answerObjectList: userAnswers };
        axios.post('/api/questions', answerDTO)
            .then(response => {
                setSubmitResponse(response.data);
                setShowScore(true);
            })
            .catch(error => console.log(error));
        navigate("/evaluation")
    };

    const handleRestart = () => {
        navigate("/");
    }

    function setSingleAnswer(submittedAnswer: UserAnswer) {
        let included = false;
        setUsersAnswer(userAnswers.map(currentAnswer => {
            if (currentAnswer.description === submittedAnswer.description) {
                included = true;
                return submittedAnswer;
            } else {
                return currentAnswer;
            }
        }));
        if (!included) {
            let updatedUserAnswers = userAnswers;
            updatedUserAnswers.push(submittedAnswer);
            setUsersAnswer(updatedUserAnswers);
        }
    }

    useEffect(() => {
        axios.get('/api/questions')
            .then(response => response.data)
            .then(data => {
                setQuestionsUnsortedList(data);
            })
            .catch(error => console.log(error));
    }, []);

    const currentQuestion = questionsUnsortedList[currentIndex];
    const isLastQuestion = currentIndex === questionsUnsortedList.length - 1;

    return (
        <div>
            {currentQuestion && (
                <QuestionCard
                    key={"questionCard_" + currentQuestion.description}
                    setSingleAnswer={setSingleAnswer}
                    description={currentQuestion.description}
                    answers={currentQuestion.answers}
                />
            )}
            {isLastQuestion ? (
                <>
                    {showScore ? (
                        <>
                            <p>Score: {submitResponse}</p>
                            <button onClick={handleRestart}>Restart</button>
                        </>
                    ) : (
                        <button onClick={handleClickEvaluation}>Evaluation</button>
                    )}
                </>
            ) : (
                <button onClick={handleNext}>Next</button>
            )}
        </div>
    );
}

export default Questions;
