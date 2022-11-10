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
@Table(name="BlogCategories")
public class BlogCategories extends BaseEntity{
    //fetch int from base entity
    @Column(name="category_name",length=45)
    private String category_name;
    @Column(name="blog_views")
    private Long blog_views;
    @Column(name ="blog_categoriescol",length=45)
    private String blog_categoriescol;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "blogCategories")
    @JsonIgnore
    private Set<BlogPosts> blogPosts = new HashSet<>();
}
