import React from 'react';
import { Result } from './Result';

type HighscoreCardProps = {
    result: Result;
};

function HighscoreCard({ result }: HighscoreCardProps) {
    return (
        <div>
            <p>Player Name: {result.playerName}</p>
            <p>Score: {result.score}</p>
        </div>
    );
}

export default HighscoreCard;
