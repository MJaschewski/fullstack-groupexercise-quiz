import React from 'react';
import {Question} from "./QuestionType";

function QuestionCard(props: Question) {


    return (
        <div>
            <h3>{Buffer.from(props.description, 'base64').toString()}</h3>
            {props.answers.map(currentAnswer => {
                return <p key={"index_" + currentAnswer}>{Buffer.from(currentAnswer, 'base64').toString()}</p>
            })}
            <button>Buffer.from(currentAnswer, 'base64').toString()</button>
        </div>
    );
}

export default QuestionCard;