package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dto.Response;
import com.example.TaskManagement.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/add_user")
    public ResponseEntity<Response> addUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        Response response = TaskManagementService.addUser(name, email, password);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PatchMapping("/update_user")
    public ResponseEntity<Response> updateUser(@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("phoneNumber") String phoneNumber ,@RequestParam("birthDate") LocalDate birthDate) {
        Response response = TaskManagementService.updateUser(name, email, phoneNumber, birthDate);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
