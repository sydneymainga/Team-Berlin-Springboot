package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
@Builder
@Data
@Entity
@AllArgsConstructor
public class BlogPosts extends BaseEntity {

    //id from base entity
    @Column(length=500)
    private String post_title;
    @Column(length=2000)
    private String blog_description;
    @Lob//A Lob annotation specifies that a persistent property or field should be persisted as a large object to a database-supported large object type.
    private String content;
    @Column(length=45)
    private String blogpostscol;
    @Column(length=45)
    private String blogpostscol1;
    private Integer accounts_id;
}
