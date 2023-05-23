import React, { useState} from 'react';
import './App.css';
import QuizForm from './components/QuizForm'
import {CategoryType} from "./components/CategoryType";
import {QuestionState} from "./Api";

function App() {
    const categories:CategoryType[] = [{"id": 9, "name": "General Knowledge"}, {"id": 10, "name": "Entertainment: Books"}, {"id": 11, "name": "Entertainment: Film"},]
    const difficultyLevels = ['Easy', 'Normal', 'Hard'];
    const [questions, setQuestions] = useState<QuestionState[]>([])

    return (
        <div>
            <div className={"App"}>
                <header><h1>Q U I Z M A S T E R 2000</h1></header>
            </div>
            <div className={"QuizSelection"}>
                <h3>Create new Quiz:</h3>
                <h4>Choose options:</h4>
                {<QuizForm difficultyLevels={difficultyLevels}
                           categories={categories.map(currentCategory => currentCategory.name)}
                           questionCount={10}
                />}
            </div>
        </div>
    );
}

export default App;
