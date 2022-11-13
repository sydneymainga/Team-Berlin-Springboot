package com.spaceyatech.berlin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="BlogPost")
public class BlogPost extends BaseEntity {

    //id from base entity
    @Column(name="post_title",length=500)
    private String post_title;
    @Column(name="blog_description",length=2000)
    private String blog_description;
    @Lob//A Lob annotation specifies that a persistent property or field should be persisted as a large object to a database-supported large object type.
    @Column(name="content")
    private String content;
    @Column(name="blog_posts_col",length=45)
    private String blogpostscol;
    @Column(name="blog_posts_col1",length=45)
    private String blogpostscol1;
    /*@Column(name="accounts_id",length=45)
    private Long accounts_id;//foreign key*/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//we use Fetchtype.LAZY for faster performance
    @JoinColumn(name = "accounts_id", nullable = false)//We also use the @JoinColumn annotation to specify the foreign key column
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "BlogCategoryHasBlogPost",
            joinColumns = { @JoinColumn(name = "blog_categories_id") },
            inverseJoinColumns = { @JoinColumn(name = "blogposts_id") })
    private Set<BlogCategory> blogCategory = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "BlogPostImageHasBlogPost",
            joinColumns = { @JoinColumn(name = "blog_post_images_blog_image_url") },
            inverseJoinColumns = { @JoinColumn(name = "blogposts_id") })
    private Set<BlogPostImage> blogPostImage = new HashSet<>();
}
