import React, { useState, useEffect } from 'react';
import HighscoreCard from './HighscoreCard';
import {ResultDTO} from "./ResultDTO";
import {Result} from "./Result";

const Highscores = () => {
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
            console.error(error);
        }
    };

    const handleFormSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/highscore', {
                method: 'POST',
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
            console.error(error);
        }
    };

    const sortedHighscores = highscores.sort((a, b) => b.score - a.score);

    const handlePlayerNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setNewHighscore((prevHighscore) => ({
            ...prevHighscore,
            playerName: value,
        }));
    };

    return (
        <div>
            <h1>Highscores</h1>
            {sortedHighscores.map((result, index) => (
                <HighscoreCard key={index} result={result} />
            ))}
            <form onSubmit={handleFormSubmit}>
                <input
                    type="text"
                    placeholder="Player Name"
                    value={newHighscore.playerName}
                    onChange={handlePlayerNameChange}
                />
                <button type="submit">Add Highscore</button>
            </form>
        </div>
    );
};

export default Highscores;
