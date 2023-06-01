package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private String score;
    private List<EvaluationQuestion> evaluationQuestionList = new ArrayList<>();
}
