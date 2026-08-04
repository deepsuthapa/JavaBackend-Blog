
package com.blog.demo.Controller;

import com.blog.demo.Models.LoginResponse;
import com.blog.demo.Models.Users;
import com.blog.demo.Models.UsersDTO;
import com.blog.demo.Service.JwtService;
import com.blog.demo.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Authentication {

    UserService userService;
    JwtService jwtService;

    public Authentication(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public UsersDTO signUp(@RequestBody Users user) {
        try {
            Users existing = userService.getUserByEmail(user.getEmail());
            if (existing == null) {
                this.userService.createUser(user);
                return new UsersDTO(user);
            }
        } catch (Exception e) {
            System.out.println("Error while registering user: " + e);
        }
        return new UsersDTO();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Users user, HttpServletResponse servletResponse) {

        Users foundUser;

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            foundUser = userService.getUserByEmail(user.getEmail());

        } else if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            foundUser = userService.getUserByPhone(user.getPhone());

        } else {
            LoginResponse response = new LoginResponse();
            response.setMessage("Please enter either email or phone.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (foundUser == null) {
            LoginResponse response = new LoginResponse();
            response.setMessage("User not found.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (!foundUser.getPassword().equals(user.getPassword())) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Invalid credentials.");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

//        HttpSession session = request.getSession();

//        session.setAttribute("userId", foundUser.getId());
//        session.setAttribute("userEmail", foundUser.getEmail());
//        System.out.println("****************************************Session data****************************************\n" + session.toString() + " " + session.getId());

        String token = jwtService.generateToken(foundUser.getEmail());
        Cookie cookie = new Cookie("jwt", token);

        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 3); // 7 days

        servletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponse(foundUser, token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        try {
            Cookie cookie = new Cookie("jwt", null);

            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(0);

            response.addCookie(cookie);
            System.out.println("user logged out: ");

            return ResponseEntity.ok(Map.of("message", "logged out"));
        } catch (Exception e) {
            System.out.println("Error while logging out: " + e);
        }
        return null;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestBody Object body){
        HttpStatus status = HttpStatus.ACCEPTED;
        System.out.println(status);
        return ResponseEntity.status(status).body(body);
    }
}
