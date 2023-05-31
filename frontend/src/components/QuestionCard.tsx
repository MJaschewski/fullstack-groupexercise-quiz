import React from 'react';
import he from 'he';
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
            <h3>{he.decode(props.description)}</h3>
            {props.answers.map(currentAnswer => {
                return <button key={"index_" + currentAnswer}
                               onClick={() => handleButtonClick(currentAnswer)}>{he.decode(currentAnswer).toString()}</button>
            })}
        </div>
    );
}

export default QuestionCard;