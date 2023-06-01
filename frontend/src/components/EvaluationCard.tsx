import React from 'react';
import { Evaluation } from "./EvaluationType";

type Props = {
    evaluation: Evaluation;
};
export default function EvaluationCard(props: Props) {
    const { description } = props.evaluation;

    return (
        <div>
            <h3>{description}</h3>
        </div>
    )
}