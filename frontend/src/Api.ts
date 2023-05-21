import {shuffleArray} from "./utils/shuffleArray";

export type Question = {
    category: string
    currentAnswer: string
    difficulty: string
    incorrectAnswers: string[]
    question: string
    type: string
}

export type QuestionState = Question & { answers: string[] }

export enum Difficulty {
    EASY = 'easy',
    MEDIUM = 'medium',
    HARD = 'hard'
}

export const fetchQuizQuestion = async (
    amount: number,
    difficulty: Difficulty
) => {
    const endpoint = 'https://localhost:3000/api/home'
    const data = await (await fetch(endpoint)).json()
    return data.results.map((question: Question) => (
        {
            ...
                question,
            answers: shuffleArray([
                ...question.incorrectAnswers,
                question.currentAnswer
            ]),
        })
    )
}