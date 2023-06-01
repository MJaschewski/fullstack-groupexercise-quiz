import React from 'react';
import { EvaluationComponment } from "./EvaluationType";
import he from "he";

type Props = {
    evaluation: EvaluationComponment;
};
export default function EvaluationCard(props: Props) {
    const { description, correctAnswer, givenAnswer } = props.evaluation;

    return (
        <div>
            <h3>{he.decode(description)}</h3>
            <p>Correct Answer: {he.decode(correctAnswer)}</p>
            <p>Given Answer: {he.decode(givenAnswer)}</p>
        </div>
    )
}