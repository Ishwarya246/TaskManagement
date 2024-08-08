package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.Response;
import com.example.TaskManagement.models.Tag;
import com.example.TaskManagement.models.Task;
import com.example.TaskManagement.models.User;
import com.example.TaskManagement.models.UserTaskMapping;
import com.example.TaskManagement.repositories.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TaskManagementService {

    public static UserRepository userRepository = null;
    public static TaskRepository taskRepository = null;
    public static UserTaskMappingRepository userTaskMappingRepository = null;
    public static TagRepository tagRepository = null;
    public static CommentRepository commentRepository = null;

    @Autowired
    public TaskManagementService(UserRepository userRepository, TaskRepository taskRepository, UserTaskMappingRepository userTaskMappingRepository, TagRepository tagRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userTaskMappingRepository = userTaskMappingRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    public static Response addUser(String name, String email, String password) {
        Optional<User> emailAlreadyExists =  userRepository.findByEmail(email);
        if (emailAlreadyExists.isPresent()) {
            return new Response("User Already Present" , true);
        }
        User user = new User(name, email, password);
        userRepository.save(user);
        return new Response("Added Successfully" , true);
    }

    public static Response updateUser(String name, String email, String phoneNumber, LocalDate birthDate) {
        Optional<User> emailAlreadyExists = userRepository.findByEmail(email);
        if (emailAlreadyExists.isEmpty()) {
            return new Response("User Not Present" , true);
        }
        User user = emailAlreadyExists.get();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setBirthDate(birthDate);
        userRepository.save(user);
        return new Response("Updated Successfully" , true);
    }

    public static User fetchUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return new User() ;
        }
        return user.get();
    }

    public static Response deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return new Response("User Not Present" , true);
        }
        userRepository.delete(user.get());
        return new Response("Deleted Successfully" , true);
    }

    public static Response addTask(Long userId, Long assigneeId, String title, String description, LocalDate startDate, LocalDate endDate, String status, int priority) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new Response("User Not Present" , true);
        }
        Optional<User> assignee = userRepository.findById(assigneeId);
        boolean b = assigneeId != -1;
        if ((assignee.isEmpty()) && b)  {
            return new Response("Assignee Not Present" , true);
        }
        Optional<Task> task = taskRepository.findByTitle(title);
        if (task.isPresent()) {
            return new Response("Task already Present" , true);
        }

        Task newTask = new Task(title, description, startDate, endDate, status, priority);
        newTask = taskRepository.save(newTask);

        UserTaskMapping userTaskMapping = new UserTaskMapping(userId, assigneeId, newTask.getId());
        userTaskMappingRepository.save(userTaskMapping);
        return new Response("Added Successfully" , true);
    }

    public static Response updateTask(Long taskId, String title, String description, LocalDate startDate, LocalDate dueDate, String status, int priority){
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        Task newTask = task.get();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setStartDate(startDate);
        newTask.setDueDate(dueDate);
        newTask.setStatus(status);
        newTask.setPriority(priority);
        taskRepository.save(newTask);
        return new Response("Updated Successfully" , true);
    }

    public static Task fetchTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Task() ;
        }
        return task.get();
    }

    public static Response deleteTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        taskRepository.delete(task.get());
        return new Response("Deleted Successfully" , true);
    }

    public static Response changeAssignee(Long userId, Long assigneeId, Long taskId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new Response("User Not Present" , true);
        }
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        Optional<User> assignee = userRepository.findById(assigneeId);
        if (assignee.isEmpty()) {
            return new Response("Assignee Not Present" , true);
        }
        Optional<UserTaskMapping> userTaskMapping = userTaskMappingRepository.findByUserIdAndTaskId(userId, taskId);
        if (userTaskMapping.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        UserTaskMapping userTaskMapping1 = userTaskMapping.get();
        userTaskMapping1.setUserId(userId);
        userTaskMapping1.setTaskId(taskId);
        userTaskMapping1.setAssigneeId(assigneeId);
        userTaskMappingRepository.save(userTaskMapping1);
        return new Response("Updated Successfully" , true);
    }

     public static List<Task> fetchUserTasks(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Task> tasks = new ArrayList<Task>();
        if (user.isEmpty()) {
            return tasks;
        }
        List<UserTaskMapping> userTasks = userTaskMappingRepository.findAllByAssigneeId(userId);

        HashMap<Integer , List<Task>> userTaskHashMap = new HashMap<>();
        userTaskHashMap.put(0, new ArrayList<Task>() );
        userTaskHashMap.put(1, new ArrayList<Task>() );
        userTaskHashMap.put(2, new ArrayList<Task>() );
        for (UserTaskMapping userTaskMapping : userTasks) {
            Task task = taskRepository.findById(userTaskMapping.getTaskId()).get();
            List<Task> list1 = userTaskHashMap.get(task.getPriority());
            list1.add(task);
            userTaskHashMap.put(task.getPriority(), list1);
        }

        for (Integer priority : userTaskHashMap.keySet()) {
            tasks.addAll(userTaskHashMap.get(priority));
        }
        return tasks;
    }

    public static Response updateStatus(Long taskId, String status) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        Task newTask = task.get();
        newTask.setStatus(status);
        taskRepository.save(newTask);
        return new Response("Updated Successfully" , true);
    }

    public static Response updatePriority(Long taskId, Integer priority) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        Task newTask = task.get();
        newTask.setPriority(priority);
        taskRepository.save(newTask);
        return new Response("Updated Successfully" , true);
    }

    public static Response addTag(Long taskId, String tagName) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        Optional<Tag> tagPresent = tagRepository.findByTaskIdAndTagName(taskId, tagName);
        if (tagPresent.isPresent()) {
            return new Response("Tag Already Present" , true);
        }
        Tag newTag = new Tag(tagName, taskId);
        tagRepository.save(newTag);
        return new Response("Added Successfully" , true);
    }

    public static Response deleteTag(Long taskId, String tagName) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new Response("Task Not Present" , true);
        }
        Optional<Tag> tagPresent = tagRepository.findByTaskIdAndTagName(taskId, tagName);
        if (tagPresent.isPresent()) {
            tagRepository.delete(tagPresent.get());
            return new Response("Deleted Successfully" , true);
        }
        return new Response("Tag Not Present" , true);
    }

    public static List<String> fetchTags(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        List<String> tag = new ArrayList<String>();
        if (task.isEmpty()) {
            return tag;
        }
        Optional<List<Tag>> tags = tagRepository.findAllByTaskId(taskId);

        for (Tag tag1 : tags.get()) {
            tag.add(tag1.getTagName());
        }
        return tag;
    }



}
