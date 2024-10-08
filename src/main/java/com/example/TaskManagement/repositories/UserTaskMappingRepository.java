package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.UserTaskMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTaskMappingRepository extends JpaRepository<UserTaskMapping, Long> {

    Optional<UserTaskMapping> findByUserIdAndTaskId(Long userId, Long taskId);
    List<UserTaskMapping> findAllByAssigneeId(Long assigneeId);
    List<UserTaskMapping> findAllByTaskId(Long taskId);
}
