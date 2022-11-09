package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
@Builder
@Data
@Entity
@AllArgsConstructor
public class Accounts extends BaseEntity{
    //fetch id from base entity class
    @Column(length=60)
    private String display_photo;
    private Integer users_id;
    @Column(length=50)
    private String name;
    @Column(length=6)
    private Timestamp CreatedAT;
    @Column(length=6)
    private Timestamp UpdatedAT;
    @Column(length=1000)
    private String bio_data;
}
