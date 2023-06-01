import React from 'react';
import { EvaluationComponment } from "./EvaluationType";

type Props = {
    evaluation: EvaluationComponment;
};
export default function EvaluationCard(props: Props) {
    const { description } = props.evaluation;

    return (
        <div>
            <h3>{description}</h3>
        </div>
    )
}