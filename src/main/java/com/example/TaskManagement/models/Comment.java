package com.example.TaskManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Lombok;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    Long userId;
    Long taskId;
    private String comment;

    public Comment() {}

    public Comment(Long userId, Long taskId, String comment) {
        this.userId = userId;
        this.taskId = taskId;
        this.comment = comment;
    }
}
