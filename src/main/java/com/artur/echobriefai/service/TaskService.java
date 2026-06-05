package com.artur.echobriefai.service;

import com.artur.echobriefai.dto.CreateTaskRequest;
import com.artur.echobriefai.dto.TaskResponse;
import com.artur.echobriefai.dto.UpdateTaskRequest;
import com.artur.echobriefai.entity.Task;
import com.artur.echobriefai.entity.User;
import com.artur.echobriefai.enums.TaskCategory;
import com.artur.echobriefai.enums.TaskStatus;
import com.artur.echobriefai.exception.TaskNotFoundException;
import com.artur.echobriefai.repository.TaskRepository;
import com.artur.echobriefai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponse create(CreateTaskRequest request) {
        User user = getCurrentUser();
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory() != null ? request.getCategory() : TaskCategory.OTHER)
                .dueDate(request.getDueDate())
                .user(user)
                .build();
        return TaskResponse.from(taskRepository.save(task));
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findByUser(getCurrentUser())
                .stream().map(TaskResponse::from).toList();
    }

    public List<TaskResponse> findByStatus(TaskStatus status) {
        return taskRepository.findByUserAndStatus(getCurrentUser(), status)
                .stream().map(TaskResponse::from).toList();
    }

    public List<TaskResponse> findByCategory(TaskCategory category) {
        return taskRepository.findByUserAndCategory(getCurrentUser(), category)
                .stream().map(TaskResponse::from).toList();
    }

    public TaskResponse findById(Long id) {
        return TaskResponse.from(
                taskRepository.findByIdAndUser(id, getCurrentUser())
                        .orElseThrow(() -> new TaskNotFoundException(id))
        );
    }

    public TaskResponse update(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findByIdAndUser(id, getCurrentUser())
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (request.getTitle() != null) task.setTitle(request.getTitle());
        if (request.getDescription() != null) task.setDescription(request.getDescription());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        if (request.getCategory() != null) task.setCategory(request.getCategory());
        if (request.getDueDate() != null) task.setDueDate(request.getDueDate());

        return TaskResponse.from(taskRepository.save(task));
    }

    public void delete(Long id) {
        if (taskRepository.findByIdAndUser(id, getCurrentUser()).isEmpty()) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }
}