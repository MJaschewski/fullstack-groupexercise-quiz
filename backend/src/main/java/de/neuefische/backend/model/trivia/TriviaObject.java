package de.neuefische.backend.model.trivia;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TriviaObject {
    List<TriviaCategories> trivia_categories;

    public TriviaObject(List<TriviaCategories> trivia_categories)
    {
        this.trivia_categories = trivia_categories;
    }
}

