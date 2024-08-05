package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dto.Response;
import com.example.TaskManagement.models.Task;
import com.example.TaskManagement.models.User;
import com.example.TaskManagement.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class TaskManagementController {

    public final TaskManagementService taskManagementService;

    @Autowired
    public TaskManagementController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @PostMapping("/home")
    public ResponseEntity<Response> homeTask() {
        Response response = new Response("Home Page" , true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add_user")
    public ResponseEntity<Response> addUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        Response response = TaskManagementService.addUser(name, email, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update_user")
    public ResponseEntity<Response> updateUser(@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("phoneNumber") String phoneNumber ,@RequestParam("birthDate") LocalDate birthDate) {
        Response response = TaskManagementService.updateUser(name, email, phoneNumber, birthDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetch_user")
    public ResponseEntity<User> fetchUser(@RequestParam("email") String email) {
        User response = TaskManagementService.fetchUser(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<Response> deleteUser(@RequestParam("email") String email) {
        Response response = TaskManagementService.deleteUser(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add_task")
    public ResponseEntity<Response> addTask(@RequestParam("userId") Long userId, @RequestParam("assigneeId") Long assigneeId, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("startDate") LocalDate startDate, @RequestParam("dueDate") LocalDate dueDate, @RequestParam("status") String status, @RequestParam("priority") int priority) {
        Response response = TaskManagementService.addTask(userId,assigneeId, title, description, startDate, dueDate, status, priority);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update_task")
    public ResponseEntity<Response> updateTask(@RequestParam("taskId") Long taskId, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("startDate") LocalDate startDate, @RequestParam("dueDate") LocalDate dueDate, @RequestParam("status") String status, @RequestParam("priority") int priority) {
        Response response = TaskManagementService.updateTask(taskId, title, description, startDate, dueDate, status, priority);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetch_task")
    public ResponseEntity<Task> fetchTask(@RequestParam("taskId") Long taskId) {
        Task response = TaskManagementService.fetchTask(taskId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete_task")
    public ResponseEntity<Response> deleteTask(@RequestParam("taskId") Long taskId) {
        System.out.println("111111111111111111111111111111");
        Response response = TaskManagementService.deleteTask(taskId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/change_assignee")
    public ResponseEntity<Response> changeAssignee(@RequestParam("userId") Long userId, @RequestParam("taskId") Long taskId, @RequestParam("assigneeId") Long assigneeId){
        Response response = TaskManagementService.changeAssignee(userId,assigneeId,taskId );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    }
