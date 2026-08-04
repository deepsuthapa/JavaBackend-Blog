package com.blog.demo.Controller;

import com.blog.demo.Models.Blogs;
import com.blog.demo.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class Posts {

    PostService postService;

    public Posts(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPosts")
    public Blogs createPosts(@RequestBody Blogs p) {
        return postService.addPost(p);
    }

    @GetMapping("/getPosts")
    public Page<Blogs> getPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
        return postService.getPosts(page, size);
    }

    @GetMapping("/getPostsByEmail/{email}")
    public Collection<Blogs> getPostsByEmail(@PathVariable String email) {
        return postService.getPostsByEmail(email);
    }

    @GetMapping("/getPostsByAuthor/{author}")
    public Collection<Blogs> getPostsByAuthor(@PathVariable String author) {
        return postService.getPostsByAuthor(author);
    }

    @GetMapping("/getPostsCount/{email}")
    public int getPostsCountByUser(@PathVariable String email) {
        return postService.getPostsCount(email);
    }

    @GetMapping("/getLikesByEmail/{email}")
    public int getLikesByEmail(@PathVariable String email) {
        return postService.getLikesByEmail(email);
    }

    @GetMapping("/search")
    public Page<Blogs> searchPosts(@RequestParam String value, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size){
        return postService.searchPosts(value, page, size);
    }

    @GetMapping("/get-post-by-id/{id}")
    public Blogs getPostById(@PathVariable int id){
        Blogs blogs;
        return postService.getPostById(id);
    }
}
