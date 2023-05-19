import React, { ChangeEvent, FormEvent, useState, Key } from 'react';

type Props = {
    questionCount: number;
    categories: string[];
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
            <br/>
            <br/>
            <label>
                Category:
                <select
                    name="category"
                    value={formData.category}
                    onChange={handleSelectChange}
                >
                    <>
                    <option value="">Please select</option>
{/*                    {categories.map((value: string, key: number) => (

                        return (
                        cat
                            <option key={key} value={value}>
                                {value}
                            </option>
                        )
                    }*/}
                    </>
                </select>
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
    );
};

export default QuizForm;
