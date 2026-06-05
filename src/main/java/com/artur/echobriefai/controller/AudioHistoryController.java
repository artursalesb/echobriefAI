package com.artur.echobriefai.controller;

import com.artur.echobriefai.dto.AudioHistoryResponse;
import com.artur.echobriefai.service.AudioHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class AudioHistoryController {

    private final AudioHistoryService audioHistoryService;

    @GetMapping
    public ResponseEntity<List<AudioHistoryResponse>> findAll() {
        return ResponseEntity.ok(audioHistoryService.findAll());
    }
}