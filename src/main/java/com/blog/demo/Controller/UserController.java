package com.blog.demo.Controller;

import com.blog.demo.Models.Users;
import com.blog.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public Collection<Users> getAllUsers(
            @RequestParam(required = false) String name) {

        if (name == null || name.isBlank()) {
            return userService.getAllUsers();
        }

        return userService.searchUsers(name);
    }

    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable int id){
        return this.userService.getUserById(id);
    }
}
