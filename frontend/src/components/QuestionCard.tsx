import React from 'react';
import {UserAnswer} from "./UserAnswerType";

type Props = {
    description: string,
    answers: string[],
    setSingleAnswer: (submittedAnswer: UserAnswer) => void
}

function QuestionCard(props: Props) {

    const handleButtonClick = (selectedAnswer: string) => {
        props.setSingleAnswer({description: props.description, answer: selectedAnswer});
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