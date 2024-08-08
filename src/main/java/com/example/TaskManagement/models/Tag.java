package com.example.TaskManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    String tagName;
    Long taskId;

    public Tag() {}
    public Tag(String tagName, Long taskId) {
        this.tagName = tagName;
        this.taskId = taskId;
    }

}
