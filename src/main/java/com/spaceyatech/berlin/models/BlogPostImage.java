package com.spaceyatech.berlin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="BlogPostImage")
public class BlogPostImage extends BaseEntity{

    @Column(name="blog_image_url",length=1000)
    private String blog_image_url;
    @Column(name="blog_post_imagescol",length=45)
    private String blog_post_imagescol;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "blogPostImage")
    @JsonIgnore
    private Set<BlogPost> blogPost = new HashSet<>();
}
