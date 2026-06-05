package com.artur.echobriefai.repository;

import com.artur.echobriefai.entity.Task;
import com.artur.echobriefai.enums.TaskCategory;
import com.artur.echobriefai.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.artur.echobriefai.entity.User;
import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndStatus(User user, TaskStatus status);
    List<Task> findByUserAndCategory(User user, TaskCategory category);
    Optional<Task> findByIdAndUser(Long id, User user);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByCategory(TaskCategory category);
}