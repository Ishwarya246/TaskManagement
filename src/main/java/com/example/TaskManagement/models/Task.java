package com.example.TaskManagement.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "task_seq", sequenceName = "task_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    private String title;
    private String description;
    private int priority;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String status;

    public Task() {}

    public Task(String title, String description,LocalDate startDate, LocalDate dueDate,  String status,  int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

}
