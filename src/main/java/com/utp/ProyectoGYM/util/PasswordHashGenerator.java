package com.utp.ProyectoGYM.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword1 = "admin123";
        String rawPassword2 = "empleado123";
        System.out.println("admin123: " + encoder.encode(rawPassword1));
        System.out.println("empleado123: " + encoder.encode(rawPassword2));
    }
}
