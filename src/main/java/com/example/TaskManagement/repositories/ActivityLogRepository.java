package com.example.TaskManagement.repositories;

import com.example.TaskManagement.models.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {

}
