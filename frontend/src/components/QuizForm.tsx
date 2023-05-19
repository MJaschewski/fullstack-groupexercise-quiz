import React, { ChangeEvent, FormEvent, useState, Key } from 'react';

type Props = {
    questionCount: number;
    categories: Map<Object, string>[];
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
            <label>
                Number of questions:
                <input
                    type="number"
                    name="questions"
                    value={formData.questions}
                    onChange={handleQuestionChange}
                />
            </label>
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
                    {categories.map((category => {
                        for(let [key,value] of category ) {
                            <option key={key.toString()} value={value}>
                                {value}
                            </option>
                        }
                    }))
                    }
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
