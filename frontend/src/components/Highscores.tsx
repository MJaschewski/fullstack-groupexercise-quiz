import React, { useState, useEffect } from 'react';
import HighscoreCard from './HighscoreCard';
import {Result} from "./Result";

const Highscores: React.FC = () => {
    const [highscores, setHighscores] = useState<Result[]>([]);

    useEffect(() => {
        fetchHighscores();
    }, []);

    const fetchHighscores = async () => {
        try {
            const response = await fetch('/api/highscore');
            const data = await response.json();
            setHighscores(data);
        } catch (error) {
            console.error('Error fetching highscores:', error);
        }
    };

    return (
        <div>
            <h1>Highscores</h1>
            {highscores.map((result, index) => (
                <HighscoreCard key={index} result={result} />
            ))}
        </div>
    );
};

export default Highscores;
