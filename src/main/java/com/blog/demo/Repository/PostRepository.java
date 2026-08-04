package com.blog.demo.Repository;

import com.blog.demo.Models.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Blogs,Integer> {

    Collection<Blogs> findAllByEmail(String email, Sort sort);
    int countByEmail(String email);

    @Query("SELECT COALESCE(SUM(b.likes), 0) FROM Blogs b WHERE b.email = :email")
    int sumLikesByEmail(String email);

    Page<Blogs> findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(String author, String title, String body, Pageable pageable);

//    @Query("""
//    SELECT b
//    FROM Blogs b
//    WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :value, '%'))
//       OR LOWER(b.title) LIKE LOWER(CONCAT('%', :value, '%'))
//       OR LOWER(b.body) LIKE LOWER(CONCAT('%', :value, '%'))
//""")
//    Collection<Blogs> searchPosts(String value);

}
