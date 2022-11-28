package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.error.BlogPostNotFoundException;
import com.spaceyatech.berlin.requests.BlogPostRequest;
import com.spaceyatech.berlin.response.BlogPostResponse;
import com.spaceyatech.berlin.services.BlogPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/") // Why is it not working when I add "blogposts"
@Slf4j
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @PostMapping(value = "blogposts")
    public ResponseEntity<?> postBlogPost(@Valid @RequestBody BlogPostRequest blogPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostService.saveBlog(blogPostRequest));
    }

    @GetMapping(value = "blogposts/{id}")
    public ResponseEntity<BlogPostResponse> fetchBlogPost(@PathVariable("id") UUID blogPostId) throws BlogPostNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(blogPostService.fetchOneBlog(blogPostId));
    }

    @GetMapping(value = "blogposts")
    public ResponseEntity<List<BlogPostResponse>> fetchAllBlogs() {
        return ResponseEntity.status(HttpStatus.OK).body(blogPostService.fetchAllBlogs());
    }

    // TODO: Update a blog
    @PutMapping(value = "blogposts/{id}")
    public ResponseEntity<?> updateBlogPost(BlogPostRequest blogPostRequest, Long blogPostId) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Not yet implemented");
    }

    // TODO: Delete a blog
    @DeleteMapping(value = "blogposts/{id}")
    public ResponseEntity<?> deleteBlogPost(Long blogPostId) throws BlogPostNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body("Not yet implemented");
    }
}