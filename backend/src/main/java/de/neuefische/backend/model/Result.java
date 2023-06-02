package de.neuefische.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Result {
    private final String id;
    private final String playerName;
    private final int score;
}
