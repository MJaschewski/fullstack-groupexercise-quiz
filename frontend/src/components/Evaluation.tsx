import React, { useEffect, useState } from 'react';
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EvaluationCard from "./EvaluationCard";
import { EvaluationComponment } from "./EvaluationType";

const Evaluation = () => {
    const [evaluationList, setEvaluationList] = useState<EvaluationComponment[]>([]);
    const [totalScore, setTotalScore] = useState<number>(0);
    const [playerName, setPlayerName] = useState<string>("");
    const [playedCategory, setPlayedCategory] = useState<string>("");

    useEffect(() => {
        axios.get("/api/evaluation")
            .then(response => {
                const data = response.data;
                setEvaluationList(data.evaluationQuestionList);
                setTotalScore(data.score);
                setPlayedCategory(data.category);
            })
            .catch(error => console.log(error));
    }, []);

    const navigate = useNavigate();

    const handleRestart = () => {
        navigate("/");
    }

    const handlePlayerNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPlayerName(event.target.value);
    }

    const handleAddHighscore = () => {
        const highscoreData = {
            playerName: playerName,
            score: totalScore,
            difficulty: playedCategory,
            category: "",
            numOfQuestions: 0
        };

        axios.post("/api/highscore", highscoreData)
            .then(response => {
                console.log("Highscore added:", response.data);
                navigate("/highscores");
            })
            .catch(error => console.error("Error adding highscore:", error));
    }

    return (
        <div>
            <h2>Evaluation</h2>
            {evaluationList.map(evaluation => (
                <EvaluationCard key={evaluation.description} evaluation={evaluation} />
            ))}
            <p>Total Score: {totalScore}</p>
            <input
                type="text"
                placeholder="Enter your name"
                value={playerName}
                onChange={handlePlayerNameChange}
            />
            <button onClick={handleAddHighscore}>Add to Highscore</button>
            <button onClick={handleRestart}>Restart</button>
        </div>
    );
}

export default Evaluation;
