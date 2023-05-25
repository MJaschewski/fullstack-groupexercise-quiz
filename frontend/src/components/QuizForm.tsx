import React, {ChangeEvent, useState} from 'react';
import './QuizForm.css'

type Props = {
    categories: React.Key[];
};

const QuizForm = ({categories }: Props) => {
    const [formData, setFormData] = useState({
        category: '',
    });

    const handleQuestionChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;

        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));

    };

    return (
        <div>
            <div>
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
            </div>
        </div>
    );
};

export default QuizForm;
