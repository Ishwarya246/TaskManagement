package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.Response;
import com.example.TaskManagement.models.User;
import com.example.TaskManagement.repositories.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TaskManagementService {

    public static UserRepository userRepository = null;

    @Autowired
    public TaskManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static Response addUser(String name, String email, String password) {
        User user = new User(name, email, password);

        Optional<User> emailAlreadyExists = userRepository.findByEmail(email);
        if (emailAlreadyExists.isPresent()) {
            return new Response("User Already Present" , true);
        }
        userRepository.save(user);
        return new Response("Added Successfully" , true);
    }

    public static Response updateUser(String name, String email, String phoneNumber, LocalDate birthDate) {
        Optional<User> emailAlreadyExists = userRepository.findByEmail(email);
        if (!emailAlreadyExists.isPresent()) {
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


}
