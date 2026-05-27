package com.artur.echobriefai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranscriptionResponse {
    private String transcription;
    private String reply;
}