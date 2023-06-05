import React, { ChangeEvent, FormEvent, useState } from 'react';
import axios from "axios";
import './QuizForm.css'
import { useNavigate } from "react-router-dom";

type Props = {
    questionCount: number;
    categories: React.Key[];
    difficultyLevels: React.Key[];
};

const QuizForm = ({ questionCount, categories, difficultyLevels }: Props) => {
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [formData, setFormData] = useState({
        questions: '',
        category: '',
        difficulty: '',
    });

    const handleQuestionChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;

        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault();
        setIsLoading(true);
        axios.post('/api/home', formData)
            .then((response) => {
                console.log(response.data);
            })
            .then(() => setIsLoading(false))
            .then(() => navigate("/questions"))
            .catch((error) => {
                console.error(error);
            });
    };

    const isFormValid = formData.category !== '' && formData.difficulty !== '' && formData.questions !== '';

    return (
        <div>
            <div className={"QuizSelection"}>
                <h3>Create new Quiz:</h3>
                <h4>Choose options:</h4>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="questions">Number of questions:
                        <input
                            type="number"
                            name="questions"
                            value={formData.questions}
                            onChange={(event: ChangeEvent<HTMLInputElement>) => {
                                const { value } = event.target;
                                if (Number(value) > 0 && Number(value) <= 20) {
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
                    <br />
                    <br />
                    <label>
                        <p>Choose Category:</p>
                        <div className="category-options">
                        {categories.map((level: React.Key) => (
                            <div key={level} className="category-option">
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
                        </div>
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
                    <br />
                    {isFormValid && (
                        <button type="submit" disabled={isLoading}>
                            {isLoading ? "Loading..." : "Submit Form"}
                        </button>
                    )}
                </form>
            </div>
        </div>
    );
};

export default QuizForm;
