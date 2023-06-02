import React from 'react';
import {Result} from "./Result";

type Props = {
    score: Result
    rankedNumber: number
}

function HighscoreCard(props:Props) {
    const {rankedNumber, score} = props;

    return (
        <div>
            <h3>{props.score.playerName}</h3>
            <h4>{props.score.score}</h4>
        </div>
    );
}

export default HighscoreCard;