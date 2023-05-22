package de.neuefische.backend.model;

import lombok.Data;

@Data
public class AnswerModel {
    String sessionID;
    String questionID;
    Integer answer;
}
