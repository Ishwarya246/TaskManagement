package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByTaskIdAndCommentAndUserId(Long taskId, String comment, Long userId);
    Optional<Comment> findByIdAndTaskId(Long id, Long taskId);
    Comment findByIdAndTaskIdAndUserId(Long id, Long taskId, Long userId);

}
