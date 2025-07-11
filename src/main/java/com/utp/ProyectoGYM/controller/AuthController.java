package com.utp.ProyectoGYM.controller;

import com.utp.ProyectoGYM.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        // Spring Security manejará esta solicitud
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDto registrationDto) {
        // TODO: Implement registration logic here
        // You can access the registration data using registrationDto.getUsername(), etc.
        return ResponseEntity.ok().build();
    }
}