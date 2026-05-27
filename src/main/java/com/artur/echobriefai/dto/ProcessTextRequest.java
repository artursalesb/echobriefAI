package com.artur.echobriefai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProcessTextRequest {

    @NotBlank
    private String text;
}