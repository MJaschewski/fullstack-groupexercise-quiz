package de.neuefische.backend.model;

import lombok.Data;

@Data
public class OpentdbModel {
    private Integer response_code;
    private OpentdbResults[] results;
}
