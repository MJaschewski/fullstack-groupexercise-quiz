import React, {useEffect, useState} from 'react';

import {Question} from "./QuestionType";
import QuestionCard from "./QuestionCard";
import axios from "axios";
import {UserAnswer} from "./UserAnswerType";


const Questions = () => {

    const [questionsUnsortedList, setQuestionsUnsortedList] = useState<Question[]>([])
    const [currentIndex, setCurrentIndex] = useState<number>(0)
    const [userAnswers, setUsersAnswer] = useState<UserAnswer[]>([])
    const handleNext =() => {
        setCurrentIndex((prevIndex) => prevIndex + 1)
    }
    const handleClickSubmit = () => {
        axios.post('/api/questions', userAnswers)
            .then(response => console.log(response))
            .catch(error => console.log(error));
    }

    function setSingleAnswer(submittedAnswer:UserAnswer){
        setUsersAnswer(userAnswers.map(currentAnswer => {
            if(currentAnswer.description === submittedAnswer.description ){
                return submittedAnswer;
            }else  {
                return currentAnswer;
            }
        }))
    }

    useEffect(() => {
        axios.get('/api/questions')
            .then(response => response.data)
            .then(data => {
                setQuestionsUnsortedList(data);
                console.log(data);
            })
            .then(
                () => setUsersAnswer(questionsUnsortedList.map(currentQuestion => {
                    return  {description:currentQuestion.description,answer:""};
                }))
            )
            .catch(error => console.log(error));
    }, []);

    const currentQuestion = questionsUnsortedList[currentIndex]
    const isLastQuestion = currentIndex === questionsUnsortedList.length - 1


    return (
        <div>
            {currentQuestion && (
                <QuestionCard
                    key={"questionCard_" + currentQuestion.description}
                    setSingleAnswer={setSingleAnswer}
                    description={currentQuestion.description}
                    answers={currentQuestion.answers}/>
            )}
            {isLastQuestion ? (
                <button onClick={handleClickSubmit}>Submit</button>
            ) : (
                <button onClick={handleNext}>Next</button>
            )}
        </div>
    );
}

export default Questions;