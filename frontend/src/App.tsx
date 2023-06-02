import React, {useEffect, useState} from 'react';
import './App.css';
import QuizForm from './components/QuizForm'
import {CategoryType} from "./components/CategoryType";
import axios from "axios";
import {Route, Routes} from "react-router-dom";
import Questions from "./components/Questions";
import Evaluation from "./components/Evaluation";

function App() {
    const difficultyLevels = ['easy', 'medium', 'hard'];
    const [categories, setCategories] = useState<CategoryType[]>([])
    const [isLoading, setIsLoading] = useState<boolean>(true);
    useEffect(() => {
        axios.get('/api/categories')
            .then(response => response.data)
            .then(data => {
                setCategories(data.trivia_categories);
                setIsLoading(false);
                console.log(data.trivia_categories);
            })
            .catch(error => console.log(error));
    }, []);

    return (
        <div>
            <title>Quizmaster 2000</title>
            <div className="App">
                <header>
                    <h1>Q U I Z M A S T E R 2000</h1>
                </header>
                {isLoading ? (
                    <p>Loading...</p>
                ) : (
                    <Routes>
                        <Route
                            path="/"
                            element={
                                <QuizForm
                                    difficultyLevels={difficultyLevels}
                                    categories={categories.map(currentCategory => currentCategory.name)}
                                    questionCount={10}
                                />
                            }
                        />
                        <Route path="/questions" element={<Questions />} />
                        <Route path="/evaluation" element={<Evaluation />} />
                    </Routes>
                )}
            </div>
        </div>
    );

}

export default App;
