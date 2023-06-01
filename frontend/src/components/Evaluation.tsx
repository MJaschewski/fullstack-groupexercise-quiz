import React, { useEffect, useState } from 'react';
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EvaluationCard from "./EvaluationCard";
import { Evaluation } from "./EvaluationType";

const Evaluation = () => {
    const [evaluationList, setEvaluationList] = useState<Evaluation[]>([]);
    const [showUserScore, setShowUserScore] = useState<boolean>(false);

    useEffect (() => {
        axios.get("/api/evaluation")
            .then(response => {
                const data = response.data;
                setEvaluationList(data.evaluationObjectList);
            })
            .catch(error => console.log(error));
    }, []);

    const navigate = useNavigate();

    const handleRestart = () => {
        navigate("/");
    }

    return (
        <div>
            <h2>Evaluation</h2>
            {evaluationList.map(evaluation => (
                <EvaluationCard key={evaluation.description} evaluation={evaluation} />
            ))}
            <button onClick={handleRestart}>Restart</button>
        </div>
    );
}

export default Evaluation;
