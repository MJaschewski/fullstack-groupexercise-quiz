import React, { useState, useEffect } from 'react';
import HighscoreCard from './HighscoreCard';
import {Result} from "./Result";
import {ResultDTO} from "./ResultDTO";

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
            // Optional: Refresh the highscores list after adding a new highscore
            fetchHighscores();
            // Clear the form inputs
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

    return (
        <div>
            <h1>Highscores</h1>
            {highscores.map((result, index) => (
                <HighscoreCard key={index} result={result} />
            ))}
            <form onSubmit={handleFormSubmit}>
                <input
                    type="text"
                    name="playerName"
                    placeholder="Player Name"
                    value={newHighscore.playerName}
                    onChange={handleInputChange}
                />
                <input
                    type="number"
                    name="score"
                    placeholder="Score"
                    value={newHighscore.score}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="difficulty"
                    placeholder="Difficulty"
                    value={newHighscore.difficulty}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="category"
                    placeholder="Category"
                    value={newHighscore.category}
                    onChange={handleInputChange}
                />
                <input
                    type="number"
                    name="numOfQuestions"
                    placeholder="Number of Questions"
                    value={newHighscore.numOfQuestions}
                    onChange={handleInputChange}
                />
                <button type="submit">Add Highscore</button>
            </form>
        </div>
    );
};

export default Highscores;
