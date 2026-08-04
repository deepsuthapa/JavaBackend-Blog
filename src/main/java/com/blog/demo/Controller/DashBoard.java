package com.blog.demo.Controller;

import com.blog.demo.Models.Users;
import com.blog.demo.Service.JwtService;
import com.blog.demo.Service.PostService;
import com.blog.demo.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DashBoard {

    private final PostService postService;
    private final JwtService jwtService;
    private final UserService userService;

    public DashBoard(
            PostService postService,
            JwtService jwtService,
            UserService userService) {

        this.postService = postService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public boolean isLoggedIn(@CookieValue(value = "jwt", required = false) String token, HttpServletResponse response) {

        System.out.println("Token: " + token);

        if (token == null) {
            System.out.println("No cookie");
            return false;
        }

        try {
            String email = jwtService.decodeTokenAndGetSubject(token);
            System.out.println("Email: " + email);
            Users user = userService.getUserByEmail(email);
            System.out.println("Email: " + user);

            if (user == null) {
                return false;
            }

            // Generate a fresh token
            String newToken = jwtService.generateToken(user.getEmail());

            Cookie cookie = new Cookie("jwt", newToken);

            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 3); // 3 days

            response.addCookie(cookie);

            return true;

        } catch (Exception e) {

            Cookie cookie = new Cookie("jwt", null);

            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(0);

            response.addCookie(cookie);

            return false;
        }
    }

    @GetMapping("/totalPosts")
    public Long totalPosts() {
        return postService.totalPosts();
    }

    @GetMapping("/totalPosts/{username}")
    public Long totalPosts(@PathVariable String username) {
        return postService.totalPosts(username);
    }
}