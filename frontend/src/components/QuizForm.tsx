import React, {ChangeEvent, FormEvent, useState} from 'react';

type Props = {
    questionCount:number,
    categories: string[],
    difficultyLevels:string[]
}

const QuizForm = (props:Props) => {
    const [formData, setFormData] = useState({
        question: '',
        category: '',
        difficulty: '',
    });

    const handleQuestionChange = (event:ChangeEvent<HTMLInputElement>) => {
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
        // Hier kannst du die Formulardaten weiterverarbeiten, z.B. sie an einen Server senden
        console.log(formData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Anzahl der Fragen:
                <input
                    type="number"
                    name="question"
                    value={formData.question}
                    onChange={handleQuestionChange}
                />
            </label>

            <label>
                Kategorie:
                <select
                    name="category"
                    value={formData.category}
                    onChange={handleSelectChange}
                >
                    <option value="">Bitte auswählen</option>
                    {props.categories.map((category) => (
                        <option key={category} value={category}>
                            {category}
                        </option>
                    ))}
                </select>
            </label>

            <label>
                Schwierigkeitsgrad:
                <select
                    name="difficulty"
                    value={formData.difficulty}
                    onChange={handleSelectChange}
                >
                    <option value="">Bitte auswählen</option>
                    {props.difficultyLevels.map((level) => (
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
