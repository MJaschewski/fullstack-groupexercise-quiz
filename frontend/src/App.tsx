import React, {useEffect, useState} from 'react';
import './App.css';
import QuizForm from './components/QuizForm'
import axios from "axios";
import {CategoryType} from "./components/CategoryType";
import {Difficulty, fetchQuizQuestion, QuestionState} from "./Api";

async function App() {
    const [categories, setCategories] = useState<CategoryType[]>([])
    const difficultyLevels = ['Easy', 'Normal', 'Hard'];
    const [questions, setQuestions] = useState<QuestionState[]>([])

    const newQuestions = await fetchQuizQuestion(
        //for example
        10, Difficulty.EASY
    )

    useEffect(() => {
        axios.get('http://localhost:3000/api/categories')
            .then(response => response.data)
            .then(data => {
                setCategories(data.trivia_categories);
                console.log(data.trivia_categories);
            })
            .catch(error => console.log(error));
    }, []);

    return (
        <div>
            <div className={"App"}>
                <header><h1>Q U I Z M A S T E R 2000</h1></header>
            </div>
            <div className={"QuizSelection"}>
                <h3>Create new Quiz:</h3>
                <h4>Choose options:</h4>
                {<QuizForm difficultyLevels={difficultyLevels}
                           categories={categories.map(currentCategory => currentCategory)} questionCount={10}/>}
            </div>
        </div>
    );
}

export default App;
