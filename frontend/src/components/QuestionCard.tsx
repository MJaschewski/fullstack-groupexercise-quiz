import React from 'react';
import he from 'he';
import {UserAnswer} from "./UserAnswerType";

type Props = {
    description: string,
    answers: string[],
    setSingleAnswer: (submittedAnswer: UserAnswer) => void
}

function QuestionCard(props: Props) {
    const [selectedAnswer, setSelectedAnswer] = React.useState<string | null>(null);

    const handleButtonClick = (selectedAnswer: string) => {
        setSelectedAnswer(selectedAnswer);
        props.setSingleAnswer({description: props.description, answer: selectedAnswer});
    }

    return (
        <div>
            <h3>{he.decode(props.description)}</h3>
            {props.answers.map(currentAnswer => {
                return <button key={"index_" + currentAnswer}
                               onClick={() => handleButtonClick(currentAnswer)}
                               style={{backgroundColor: selectedAnswer === currentAnswer ? "green" : 'initial'}}>{he.decode(currentAnswer)}
                </button>
            })}
        </div>
    );
}

export default QuestionCard;