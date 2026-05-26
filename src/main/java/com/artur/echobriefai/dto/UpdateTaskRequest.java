package com.artur.echobriefai.dto;

import com.artur.echobriefai.enums.TaskCategory;
import com.artur.echobriefai.enums.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UpdateTaskRequest {

    private String title;
    private String description;
    private TaskStatus status;
    private TaskCategory category;
    private LocalDateTime dueDate;
}