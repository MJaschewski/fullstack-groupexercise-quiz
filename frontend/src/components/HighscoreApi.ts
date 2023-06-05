import {Result} from "./Result";
import {useState} from "react";
import axios from "axios";

const HighscoreApi = () => {
    const [highscores, setHighscores] = useState<Result[]>([]);

    const getHighscores = () => {
        axios
            .get("api/highscores")
            .then((response) => {
                setHighscores(response.data);
            })
            .catch((error) => {
                console.log(error);
            })
    };
    return {highscores, getHighscores};
}
export default HighscoreApi;