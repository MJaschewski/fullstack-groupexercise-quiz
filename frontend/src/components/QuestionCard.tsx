import React from 'react';
import he from 'he';
import {Question} from "./QuestionType";

function QuestionCard(props: Question) {


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