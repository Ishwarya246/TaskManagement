package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
    Optional<Task> findById(Long id);
}
