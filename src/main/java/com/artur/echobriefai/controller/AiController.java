package com.artur.echobriefai.controller;

import com.artur.echobriefai.ai.AudioAiService;
import com.artur.echobriefai.dto.ProcessTextRequest;
import com.artur.echobriefai.dto.ProcessTextResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AudioAiService audioAiService;

    @PostMapping("/process")
    public ResponseEntity<ProcessTextResponse> process(@Valid @RequestBody ProcessTextRequest request) {
        String reply = audioAiService.processText(request.getText());
        return ResponseEntity.ok(new ProcessTextResponse(reply));
    }
}