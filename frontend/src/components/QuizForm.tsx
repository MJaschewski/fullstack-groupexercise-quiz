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
        <div>
        <button onClick={()=>console.log(categories)}> Categories</button>
        <form
            action="http://localhost:3000/api/home"
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
            <label htmlFor="category">Choose a category: </label>

                <select id="category">
                    <option value="">Please select</option>{
                        categories.map(currentCategory => {
                           return <option value={currentCategory}> {currentCategory} </option>
                        })
                    }
                </select>


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
