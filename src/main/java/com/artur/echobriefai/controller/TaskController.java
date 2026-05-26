package com.artur.echobriefai.controller;

import com.artur.echobriefai.dto.CreateTaskRequest;
import com.artur.echobriefai.dto.TaskResponse;
import com.artur.echobriefai.dto.UpdateTaskRequest;
import com.artur.echobriefai.enums.TaskCategory;
import com.artur.echobriefai.enums.TaskStatus;
import com.artur.echobriefai.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskCategory category
    ) {
        if (status != null) return ResponseEntity.ok(taskService.findByStatus(status));
        if (category != null) return ResponseEntity.ok(taskService.findByCategory(category));
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}