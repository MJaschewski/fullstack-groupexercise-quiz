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

    const currentQuestion = questionsUnsortedList[currentIndex];


    return (
        <div>
            {currentQuestion && (
                <QuestionCard
                    key={"questionCard_" + currentQuestion.description}
                    setSingleAnswer={setSingleAnswer}
                    description={currentQuestion.description}
                    answers={currentQuestion.answers}/>
            )}
            <button onClick={handleNext}>Next</button>
        </div>
    );
}

export default Questions;