package com.artur.echobriefai.dto;

import com.artur.echobriefai.entity.AudioHistory;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AudioHistoryResponse {

    private Long id;
    private String transcription;
    private String reply;
    private LocalDateTime createdAt;

    public static AudioHistoryResponse from(AudioHistory h) {
        return AudioHistoryResponse.builder()
                .id(h.getId())
                .transcription(h.getTranscription())
                .reply(h.getReply())
                .createdAt(h.getCreatedAt())
                .build();
    }
}