package com.spaceyatech.berlin.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostRequest {
    private String blogPostTitle;
    private String blogPostDescription;
    private String blogPostContent;
}