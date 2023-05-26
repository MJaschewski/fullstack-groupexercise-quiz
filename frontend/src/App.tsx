import React, {useEffect, useState} from 'react';
import './App.css';
import QuizForm from './components/QuizForm'
import {CategoryType} from "./components/CategoryType";
import axios from "axios";
import {Route, Routes} from "react-router-dom";
import Questions from "./components/Questions";

function App() {
    const difficultyLevels = ['easy', 'normal', 'hard'];
    const [categories, setCategories] = useState<CategoryType[]>([])
    useEffect(() => {
        axios.get('/api/categories')
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
            <Routes>
                <Route path={"/"} element={<QuizForm difficultyLevels={difficultyLevels}
                                                         categories={categories.map(currentCategory => currentCategory.name)}
                                                         questionCount={10}/>}></Route>
            </Routes>
        </div>

    );
}

export default App;
