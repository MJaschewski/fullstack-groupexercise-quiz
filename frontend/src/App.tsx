import React, {useEffect, useState} from 'react';
import './App.css';
import QuizForm from './components/QuizForm'
import {CategoryType} from "./components/CategoryType";
import axios from "axios";

function App() {
  const [categories, setCategories] = useState<CategoryType[]>([])
  useEffect(() => {
    axios.get('http://localhost:3000/api/categories')
        .then(response => response.data)
        .then(data => {
          setCategories(data.trivia_categories);
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
          {<QuizForm categories={categories.map(currentCategory => currentCategory.name)}/>}
        </div>
      </div>
  );
}

export default App;
