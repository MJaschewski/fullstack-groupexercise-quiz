package de.neuefische.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TriviaObject {
    List<TriviaCategories> trivia_categories;
    public void add(TriviaCategories trivia_categories) {
        this.trivia_categories.add(trivia_categories);
    }
}

