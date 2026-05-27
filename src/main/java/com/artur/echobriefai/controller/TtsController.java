package com.artur.echobriefai.controller;

import com.artur.echobriefai.ai.TtsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tts")
@RequiredArgsConstructor
public class TtsController {

    private final TtsService ttsService;

    @GetMapping(produces = "audio/mpeg")
    public ResponseEntity<byte[]> synthesize(@RequestParam String text) {
        byte[] audio = ttsService.synthesize(text);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=response.mp3")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audio);
    }
}