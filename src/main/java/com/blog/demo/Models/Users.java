package com.blog.demo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(unique = true, nullable = false)
    String email;
    @Column(unique = true, nullable = false)
    String phone;
    @Column(nullable = false)
    String password;
    String address;
    String bio;
    String colors;
    String message;
}
