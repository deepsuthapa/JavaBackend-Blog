package com.blog.demo.Service;

import com.blog.demo.Models.LoginResponse;
import com.blog.demo.Models.Users;
import com.blog.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void createUser(Users user) {
        repo.save(user);
    }

    public Users getUserByEmail(String email) {
        return (Users) repo.findByEmail(email).orElse(null);
    }

    public Users getUserByPhone(String phone) {
        return (Users) repo.findByPhone(phone).orElse(null);
    }

    public Collection<Users> getAllUsers() {
        return repo.findAll();
    }

    public Users getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Collection<Users> searchUsers(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
}
