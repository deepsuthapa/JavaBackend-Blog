package com.blog.demo.Repository;

import com.blog.demo.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByEmailContaining(String email);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhone(String phone);
    Collection<Users> findByNameContainingIgnoreCase(String name);
}