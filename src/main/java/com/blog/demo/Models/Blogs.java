package com.blog.demo.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter @Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Posts")
public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String email;
    private String author;
    private String url;
    Date date;
    private String title;
    private String body;
    @Builder.Default
    private Integer likes = 0;
}
