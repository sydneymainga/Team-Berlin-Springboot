package com.spaceyatech.berlin.services;

import com.spaceyatech.berlin.error.BlogPostNotFoundException;
import com.spaceyatech.berlin.requests.BlogPostRequest;
import com.spaceyatech.berlin.response.BlogPostResponse;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPostResponse saveBlog(BlogPostRequest blogPostRequest);
    BlogPostResponse fetchOneBlog(UUID blogPostId) throws BlogPostNotFoundException;

    List<BlogPostResponse> fetchAllBlogs();
}