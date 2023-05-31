import React from 'react';
import he from 'he';
import {Question} from "./QuestionType";
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
                return <p key={"index_" + currentAnswer}>{he.decode(currentAnswer).toString()}</p>
            })}
        </div>
    );
}

export default QuestionCard;