package com.artur.echobriefai.repository;

import com.artur.echobriefai.entity.Task;
import com.artur.echobriefai.enums.TaskCategory;
import com.artur.echobriefai.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByCategory(TaskCategory category);
}