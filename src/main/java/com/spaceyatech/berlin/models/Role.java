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
public class Role extends BaseEntity{
    //id from base entity
    @Column(length=45)
    private String name;
    @Column(length=45)
    private String rolecol;
}
