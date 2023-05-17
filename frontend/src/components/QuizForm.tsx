import React, { ChangeEvent, FormEvent, useState } from 'react';

type Props = {
    questionCount: number;
    categories: string[];
    difficultyLevels: string[];
};

const QuizForm = ({ questionCount, categories, difficultyLevels }: Props) => {
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

    const handleSelectChange = (event: ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    return (
        <form
            action="http://localhost:8080/api/home"
            method="POST"
        >
            <label>
                Number of questions:
                <input
                    type="number"
                    name="question"
                    value={formData.questions}
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

            <button type="submit">Submit Form</button>
        </form>
    );
};

export default QuizForm;
