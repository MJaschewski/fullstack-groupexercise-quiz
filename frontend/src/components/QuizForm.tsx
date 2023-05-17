import React, { ChangeEvent, FormEvent, useState } from 'react';
import axios from "axios";

type Props = {
    questionCount: number;
    categories: string[];
    difficultyLevels: string[];
};

const QuizForm = ({ questionCount, categories, difficultyLevels }: Props) => {
    const [formData, setFormData] = useState({
        question: '',
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

    const handleSelectChange = (event: ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault();

        axios.post('http://localhost:8080/api/home', formData)
            .then((response) => {
                // Erfolgreiche Verarbeitung der Serverantwort
                console.log(response.data);
            })
            .catch((error) => {
                // Fehler beim Posten der Daten
                console.error(error);
            });
    };


    return (
        <form onSubmit={handleSubmit}>
            <label>
                Number of questions:
                <input
                    type="number"
                    name="question"
                    value={formData.question}
                    onChange={handleQuestionChange}
                />
            </label>

            <label>
                Category:
                <select
                    name="category"
                    value={formData.category}
                    onChange={handleSelectChange}
                >
                    <option value="">Please select</option>
                    {categories.map((category) => (
                        <option key={category} value={category}>
                            {category}
                        </option>
                    ))}
                </select>
            </label>

            <label>
                Difficulty:
                <select
                    name="difficulty"
                    value={formData.difficulty}
                    onChange={handleSelectChange}
                >
                    <option value="">Please select</option>
                    {difficultyLevels.map((level) => (
                        <option key={level} value={level}>
                            {level}
                        </option>
                    ))}
                </select>
            </label>

            <button type="submit">Formular absenden</button>
        </form>
    );
};

export default QuizForm;
