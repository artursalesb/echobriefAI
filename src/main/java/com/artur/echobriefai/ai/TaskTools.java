package com.artur.echobriefai.ai;

import com.artur.echobriefai.dto.CreateTaskRequest;
import com.artur.echobriefai.enums.TaskCategory;
import com.artur.echobriefai.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskTools {

    private final TaskService taskService;

    @Tool(description = "Creates a task extracted from user audio or text. Use this when the user mentions something they need to do, remember, buy, schedule or pay.")
    public String createTask(
            @ToolParam(description = "Short and clear task title") String title,
            @ToolParam(description = "Category: HEALTH, FINANCE, WORK, PERSONAL, SHOPPING or OTHER") String category
    ) {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle(title);

        try {
            request.setCategory(TaskCategory.valueOf(category.toUpperCase()));
        } catch (IllegalArgumentException e) {
            request.setCategory(TaskCategory.OTHER);
        }

        taskService.create(request);
        return "Task created: " + title;
    }
}