import React from 'react';
import { EvaluationComponment } from "./EvaluationType";

type Props = {
    evaluation: EvaluationComponment;
};
export default function EvaluationCard(props: Props) {
    const { description, correctAnswer, givenAnswer } = props.evaluation;

    return (
        <div>
            <h3>{description}</h3>
            <p>Correct Answer: {correctAnswer}</p>
            <p>Given Answer: {givenAnswer}</p>
        </div>
    )
}