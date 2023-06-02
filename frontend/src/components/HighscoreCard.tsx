import React from 'react';
import { Result } from './Result';

type HighscoreCardProps = {
    result: Result;
};

const HighscoreCard: React.FC<HighscoreCardProps> = ({ result }) => {
    return (
        <div>
            <p>Player Name: {result.playerName}</p>
            <p>Score: {result.score}</p>
        </div>
    );
};

export default HighscoreCard;