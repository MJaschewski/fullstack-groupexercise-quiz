import React, {useState} from 'react';
import {Question} from "./QuestionType";

function QuestionCard(props: Question) {
    const [userAnswer, setUserAnswer] = useState<{ description: string, answer: string }>()

    const handleButtonClick = (selectedAnswer: string) => {
        return setUserAnswer({description: props.description, answer: selectedAnswer});
    }

    return (
        <div>
            <h3>{Buffer.from(props.description, 'base64').toString()}</h3>
            {props.answers.map(currentAnswer => {
                const decodedAnswer = Buffer.from(currentAnswer, 'base64').toString();
                return <button key={"index_" + currentAnswer} onClick={()=>handleButtonClick(decodedAnswer)}>{decodedAnswer}</button>
            })}
        </div>
    );
}

export default QuestionCard;