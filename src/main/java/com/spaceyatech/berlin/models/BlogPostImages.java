package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@Data
@Entity
@AllArgsConstructor
public class BlogPostImages {
    @Column(length=1000)
    private String blog_image_url;
    @Column(length=45)
    private String blog_post_imagescol;
}
