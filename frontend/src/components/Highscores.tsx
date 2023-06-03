import React, { useState, useEffect } from 'react';
import HighscoreCard from './HighscoreCard';
import {ResultDTO} from "./ResultDTO";
import {Result} from "./Result";
import {useNavigate} from "react-router-dom";

const Highscores = () => {
    const [highscores, setHighscores] = useState<Result[]>([]);
    const [newHighscore, setNewHighscore] = useState<ResultDTO>({
        playerName: '',
        score: 0,
        difficulty: '',
        category: '',
        numOfQuestions: 0,
    });
    const navigate = useNavigate();

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



    const sortedHighscores = highscores.sort((a, b) => b.score - a.score);

    const handleSubmitPlayerName = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setNewHighscore((prevHighscore) => ({
            ...prevHighscore,
            playerName: value,
        }));
    };

    function handleBackToQuiz() {
        navigate("/");
    }

    return (
        <div>
            <h1>Highscores</h1>
            {sortedHighscores.map((result, index) => (
                <HighscoreCard key={index} result={result} />
            ))}
                <button onClick={handleBackToQuiz}>Back to Quiz</button>
        </div>
    );
};

export default Highscores;
