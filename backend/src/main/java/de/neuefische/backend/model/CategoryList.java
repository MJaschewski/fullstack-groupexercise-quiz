package de.neuefische.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CategoryList {
    private List<CategoryObject> trivia_categories;
}

