package com.example.TaskManagement.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "ActivityLog")
public class ActivityLog {

    private String id;
    private String userId;
    private String activity;
    private String taskId;
}
