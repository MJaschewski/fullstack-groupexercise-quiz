import React, { useState, useEffect } from 'react';
import HighscoreCard from './HighscoreCard';
import {ResultDTO} from "./ResultDTO";
import {Result} from "./Result";

const Highscores: React.FC = () => {
    const [highscores, setHighscores] = useState<Result[]>([]);
    const [newHighscore, setNewHighscore] = useState<ResultDTO>({
        playerName: '',
        score: 0,
        difficulty: '',
        category: '',
        numOfQuestions: 0,
    });

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

    const handleFormSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/highscore', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newHighscore),
            });
            const data = await response.json();
            console.log('Highscore added:', data);
            fetchHighscores();
            setNewHighscore({
                playerName: '',
                score: 0,
                difficulty: '',
                category: '',
                numOfQuestions: 0,
            });
        } catch (error) {
            console.error('Error adding highscore:', error);
        }
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setNewHighscore((prevHighscore) => ({
            ...prevHighscore,
            [name]: value,
        }));
    };

    const sortedHighscores = highscores.sort((a, b) => b.score - a.score);

    return (
        <div>
            <h1>Highscores</h1>
            {sortedHighscores.map((result, index) => (
                <HighscoreCard key={index} result={result} />
            ))}
            <form onSubmit={handleFormSubmit}>
                {/* Form inputs */}
                <button type="submit">Add Highscore</button>
            </form>
        </div>
    );
};

export default Highscores;
