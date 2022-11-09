package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Builder
@Data
@Entity
@AllArgsConstructor
public class BlogCategoriesHasBlogPosts {

   private Integer blog_categories_id;
   private Integer blogposts_id;
}
