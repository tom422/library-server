package com.example.libraryservice.controller.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String username;
    private String password;
    private String newPass;
}
