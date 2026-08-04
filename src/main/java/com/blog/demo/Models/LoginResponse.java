package com.blog.demo.Models;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse {
    int id;
    String name;
    String email;
    String phone;
    String bio;
    String message;
    String colors;
    String token = null;
    public LoginResponse(Users user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.message = "Login successful";
        this.colors = user.getColors();
        this.token =  token;
//        this.session = request.getSession(false);
    }

    public LoginResponse(){
    }
}
