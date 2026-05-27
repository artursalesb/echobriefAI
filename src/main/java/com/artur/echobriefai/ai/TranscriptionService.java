package com.artur.echobriefai.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranscriptionService {

    @Value("${groq.api-key}")
    private String apiKey;

    private final AudioAiService audioAiService;

    public String transcribeAndProcess(MultipartFile audio) throws IOException {
        String transcription = transcribe(audio);
        return audioAiService.processText(transcription);
    }

    private String transcribe(MultipartFile audio) throws IOException {
        RestClient client = RestClient.create();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(audio.getBytes()) {
            @Override
            public String getFilename() {
                return audio.getOriginalFilename() != null ? audio.getOriginalFilename() : "audio.webm";
            }
        });
        body.add("model", "whisper-large-v3");
        body.add("language", "pt");
        body.add("response_format", "json");

        Map response = client.post()
                .uri("https://api.groq.com/openai/v1/audio/transcriptions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .body(Map.class);

        return (String) response.get("text");
    }
}