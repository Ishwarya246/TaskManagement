package com.example.TaskManagement.models;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;


    public User() {}

    public User(String email, String password, String name, String phoneNumber, LocalDate birthDate ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String name, String phoneNumber, LocalDate birthDate ) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }


}
