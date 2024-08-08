package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
