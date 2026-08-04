package com.blog.demo.Service;

import com.blog.demo.Models.Blogs;
import com.blog.demo.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostService {

    PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }

    public Blogs addPost(Blogs p) {
        return repo.save(p);
    }

    public Page<Blogs> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    public Collection<Blogs> getPostsByEmail(String email) {
        return repo.findAllByEmail(email, Sort.by("date").descending());
    }

    public Collection<Blogs> getPostsByAuthor(String email) {
        return repo.findAllByEmail(email, Sort.by("date").descending());
    }

    public Long totalPosts() {
        return repo.count();
    }

    public Page<Blogs> searchPosts(String value, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return repo.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(value, value, value, pageable);
    }
//*********************** for SQL query of search ***********************
//    public Collection<Blogs> searchPosts(String value) {
//        return repo.searchPosts(value);
//    }

    public Long totalPosts(String email) {
        return (long) repo.findAllByEmail(email, Sort.by("date").descending()).size();
    }

    public int getPostsCount(String email) {
        return repo.countByEmail(email);
    }

    public int getLikesByEmail(String email) {
        return repo.sumLikesByEmail(email);
    }

    public Blogs getPostById(int id) {
        return repo.findById(id).orElse(null);
    }
}
