package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
