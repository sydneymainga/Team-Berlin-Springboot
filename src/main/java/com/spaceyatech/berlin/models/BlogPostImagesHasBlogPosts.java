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
public class BlogPostImagesHasBlogPosts{

    @Column(length=1000)
    private String blog_post_images_blog_image_url;
    private Integer blogposts_id;
}
