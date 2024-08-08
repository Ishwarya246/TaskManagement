package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTaskIdAndTagName(Long taskId, String tagName);
    Optional<List<Tag>> findAllByTaskId(Long taskId);
}
