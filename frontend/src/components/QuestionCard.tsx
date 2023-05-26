import React from 'react';
import {Question} from "./QuestionType";

function QuestionCard(props: Question) {
    return (
        <div>
            <h3>{props.description}</h3>
            {props.answers.map(currentAnswer => {
                return <p key={'index'}>{currentAnswer}</p>
            })}
        </div>
    );
}

export default QuestionCard;