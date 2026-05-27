package com.artur.echobriefai.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class TtsService {

    @Value("${elevenlabs.api-key}")
    private String apiKey;

    @Value("${elevenlabs.voice-id}")
    private String voiceId;

    public byte[] synthesize(String text) {
        RestClient client = RestClient.create();

        return client.post()
                .uri("https://api.elevenlabs.io/v1/text-to-speech/" + voiceId)
                .header("xi-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of(
                        "text", text,
                        "model_id", "eleven_multilingual_v2",
                        "voice_settings", Map.of(
                                "stability", 0.5,
                                "similarity_boost", 0.75
                        )
                ))
                .retrieve()
                .body(byte[].class);
    }
}