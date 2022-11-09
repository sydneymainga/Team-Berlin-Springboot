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
public class BlogCategories extends BaseEntity{
    //fetch int from base entity
    @Column(length=45)
    private String category_name;
    private Integer blog_views;
    @Column(length=45)
    private String blog_categoriescol;
}
