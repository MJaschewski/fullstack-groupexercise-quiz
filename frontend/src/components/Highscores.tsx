import React, { useState, useEffect } from 'react';
import HighscoreCard from './HighscoreCard';
import {Result} from "./Result";
import {useNavigate} from "react-router-dom";
import axios from "axios";

const Highscores = () => {
    const [highscores, setHighscores] = useState<Result[]>([]);
    const navigate = useNavigate();

    const fetchHighscores = async () => {
        try {
            const response = await axios.get('/api/highscore');
            setHighscores(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchHighscores().catch((error) => {
            console.error(error);
        });
    }, []);



    const sortedHighscores = highscores.sort((a, b) => b.score - a.score);

    function handleBackToQuiz() {
        navigate("/");
    }

    return (
        <div>
            <h1>Highscores</h1>
            {sortedHighscores.map((result, index) => (
                <HighscoreCard key={result.id} result={result} />
            ))}
                <button onClick={handleBackToQuiz}>Back to Quiz</button>
        </div>
    );
};

export default Highscores;
