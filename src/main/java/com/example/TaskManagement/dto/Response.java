package com.example.TaskManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private String message;
    private Boolean status;
    public Response(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
