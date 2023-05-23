import React, { ChangeEvent, FormEvent, useState, Key } from 'react';
import axios from "axios";
import './QuizForm.css'

type Props = {
    questionCount: number;
    categories: React.Key[];
    difficultyLevels: React.Key[];
};

const QuizForm = ({ questionCount, categories, difficultyLevels }: Props) => {
    const [formData, setFormData] = useState({
        questions: '',
        category: '',
        difficulty: '',
    });

    const handleQuestionChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;

        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));

    };

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault();
        axios.post('http://localhost:3000/api/home', formData)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <div>
        <form onSubmit={handleSubmit}>
            <label htmlFor="questions">Number of questions:
                <input
                    type="number"
                    name="questions"
                    value={formData.questions}
                    onChange={(event: ChangeEvent<HTMLInputElement>) => {
                        const { value } = event.target;
                        if (Number(value) > 0) {
                            handleQuestionChange(event);
                        } else {
                            setFormData((prevData) => ({
                                ...prevData,
                                questions: "10",
                            }));
                        }
                    }}
                />
            </label>
            <br/>
            <br/>
            <label>
                <p>Choose Category:</p>
                {categories.map((level: React.Key) => (
                    <div key={level}>
                        <input
                            type="radio"
                            id={level.toString()}
                            name="category"
                            value={level.toString()}
                            onChange={handleQuestionChange}
                            checked={formData.category === level.toString()}
                        />
                        <label htmlFor={level.toString()}>{level.toString()}</label>
                    </div>
                ))}
            </label>
            <label>
                <p>Choose difficulty:</p>
                {difficultyLevels.map((level: React.Key) => (
                    <div key={level}>
                        <input
                            type="radio"
                            id={level.toString()}
                            name="difficulty"
                            value={level.toString()}
                            onChange={handleQuestionChange}
                            checked={formData.difficulty === level.toString()}
                        />
                        <label htmlFor={level.toString()}>{level.toString()}</label>
                    </div>
                ))}
            </label>
            <br/>
            <button type="submit">Submit Form</button>
        </form>
        </div>
    );
};

export default QuizForm;
