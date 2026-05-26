package com.artur.echobriefai.dto;

import com.artur.echobriefai.enums.TaskCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private TaskCategory category;
    private LocalDateTime dueDate;
}