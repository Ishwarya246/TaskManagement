package com.example.TaskManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_task_mapping")
public class UserTaskMapping {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long taskId;

    public UserTaskMapping() {}
    public UserTaskMapping(Long taskId, Long userId) {
        this.taskId = taskId;
        this.userId = userId;
    }
}
