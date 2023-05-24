package de.neuefische.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TriviaApiResponse {
    private int response_code;
    private List<Question> results;
}
