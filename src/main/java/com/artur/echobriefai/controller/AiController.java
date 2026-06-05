package com.artur.echobriefai.controller;

import com.artur.echobriefai.ai.AudioAiService;
import com.artur.echobriefai.ai.TranscriptionService;
import com.artur.echobriefai.dto.ProcessTextRequest;
import com.artur.echobriefai.dto.ProcessTextResponse;
import com.artur.echobriefai.dto.TranscriptionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AudioAiService audioAiService;
    private final TranscriptionService transcriptionService;

    @PostMapping("/process")
    public ResponseEntity<ProcessTextResponse> process(@Valid @RequestBody ProcessTextRequest request) {
        String reply = audioAiService.processText(request.getText());
        return ResponseEntity.ok(new ProcessTextResponse(reply));
    }

    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TranscriptionResponse> processAudio(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> result = transcriptionService.transcribeAndProcess(file);
        return ResponseEntity.ok(new TranscriptionResponse(
                result.get("transcription"),
                result.get("reply")
        ));
    }
}