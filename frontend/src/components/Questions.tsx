import React, {useEffect, useState} from 'react';

import {Question} from "./QuestionType";
import QuestionCard from "./QuestionCard";
import axios from "axios";


const Questions = () => {

    const [questionsUnsortedList, setQuestionsUnsortedList] = useState<Question[]>([])
    useEffect(() => {
    axios.get('/api/questions')
        .then(response => response.data)
        .then(data => {
            setQuestionsUnsortedList(data);
            console.log(data);
        })
        .catch(error => console.log(error));
    }, []);

    return (
        <div>
            {questionsUnsortedList.map((currentQuestion: Question) => <QuestionCard key={"questionCard"}
                                                                                    description={currentQuestion.description}
                                                                                    answers={currentQuestion.answers}/>)}
        </div>
    );
}

export default Questions;