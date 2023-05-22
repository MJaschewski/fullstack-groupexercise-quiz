package de.neuefische.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TriviaObject {
    List<TriviaCategories> triviaCategories;

    public void add(TriviaCategories trivia_categories) {
        this.triviaCategories.add(trivia_categories);
    }
}

