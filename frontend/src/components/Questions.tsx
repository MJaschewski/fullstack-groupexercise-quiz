import React, {useEffect, useState} from 'react';
import {Question} from "./QuestionType";
import QuestionCard from "./QuestionCard";
import axios from "axios";
import {UserAnswer} from "./UserAnswerType";
import {useNavigate} from "react-router-dom";
import {CircularProgressbar} from "react-circular-progressbar";

type AnswerDTO = {
    answerObjectList: UserAnswer[]
}

const Questions = () => {
    const [questionsUnsortedList, setQuestionsUnsortedList] = useState<Question[]>([]);
    const [currentIndex, setCurrentIndex] = useState<number>(0);
    const [userAnswers, setUsersAnswer] = useState<UserAnswer[]>([]);
    const [submitResponse, setSubmitResponse] = useState<any>(null);
    const [showScore, setShowScore] = useState<boolean>(false);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [timer, setTimer] = useState<number>(15);
    const navigate = useNavigate();

    const handleNext = () => {
        setCurrentIndex((prevIndex) => prevIndex + 1);
        setTimer(15)
    }

    const handleClickEvaluation = () => {
        const answerDTO: AnswerDTO = {answerObjectList: userAnswers};
        setIsLoading(true);
        axios.post('/api/questions', answerDTO)
            .then(response => {
                setSubmitResponse(response.data);
                setShowScore(true);
            })
            .then(() => setIsLoading(false))
            .then(() => navigate("/evaluation"))
            .catch(error => console.log(error));
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
            .catch(error => console.log(error))
            .finally(() => setIsLoading(false));
    }, []);

    const currentQuestion = questionsUnsortedList[currentIndex];
    const isLastQuestion = currentIndex === questionsUnsortedList.length - 1;

    useEffect(() => {
        if (timer === 0 && !isLastQuestion && !showScore) {
            const noneAnswer: UserAnswer = {
                description: currentQuestion.description,
                answer: "none"
            };
            setSingleAnswer(noneAnswer);
            handleNext();
        } else if (timer === 0 && isLastQuestion && !showScore) {
            const noneAnswer: UserAnswer = {
                description: currentQuestion.description,
                answer: "none"
            };
            setSingleAnswer(noneAnswer);
            handleClickEvaluation();
        }
        const countdown = setInterval(() => {
            setTimer((prevTimer) => prevTimer - 1)
        }, 1000);
        return () => clearInterval(countdown);
    }, [timer, isLastQuestion, showScore]);


    return (
        <div>
            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <>
                    <div style={{ marginBottom: '20px' }}>
                        {!showScore && (
                            <CircularProgressbar
                                value={timer}
                                maxValue={15}
                                text={`${timer} seconds`}
                                styles={{
                                    root: { width: '100px' },
                                    path: { stroke: `rgba(62, 152, 199, ${1 - timer / 15})` },
                                    trail: { stroke: '#f2f2f2' },
                                    text: { fill: '#000', fontSize: '16px' },
                                }}
                            />
                        )}
                    </div>
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
                </>
            )}
        </div>
    );
}

export default Questions;