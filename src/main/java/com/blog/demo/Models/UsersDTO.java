package com.blog.demo.Models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsersDTO {
    String name = null;
    boolean exists;

    public UsersDTO(Users user) {
        this.name = user.getName();
        this.exists = false;
    }

    public UsersDTO() {
        this.exists = true;
    }
}
