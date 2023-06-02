import React, {useEffect, useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import EvaluationCard from "./EvaluationCard";
import {EvaluationComponment} from "./EvaluationType";

const Evaluation = () => {
    const [evaluationList, setEvaluationList] = useState<EvaluationComponment[]>([]);
    const [totalScore, setTotalScore] = useState<number>(0);

    useEffect (() => {
        setEvaluationList([])
        setTotalScore(0)
        axios.get("/api/evaluation")
            .then(response => {
                const data = response.data;
                setEvaluationList(data.evaluationQuestionList);
                setTotalScore(data.score);
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
                <EvaluationCard key={evaluation.description} evaluation={evaluation}/>
            ))}
            <p>Total Score: {totalScore}</p>
            <button onClick={handleRestart}>Restart</button>
        </div>
    );
}

export default Evaluation;
