package de.neuefische.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class TriviaObject {
    List<TriviaCategories> trivia_categories;

    public void add(TriviaCategories trivia_categories) {
        this.trivia_categories.add(trivia_categories);
    }

    public TriviaObject(List<TriviaCategories> trivia_categories)
    {
        this.trivia_categories = trivia_categories;
    }

    public TriviaObject() {
    }
}

