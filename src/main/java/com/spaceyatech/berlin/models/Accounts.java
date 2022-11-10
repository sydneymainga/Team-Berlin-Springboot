package com.spaceyatech.berlin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Accounts")
public class Accounts extends BaseEntity{
    //fetch id from base entity class
    @Column(name="display_photo",length=60)
    private String display_photo;
    @Column(name="name",length=50)
    private String name;
    @Column(name="CreatedAT",length=6)
    private Timestamp CreatedAT;
    @Column(name="UpdatedAT",length=6)
    private Timestamp UpdatedAT;
    @Column(name ="bio_data",length=1000)
    private String bio_data;
    /*@Column(name="users_id")//foreign-KEY
    private Long users_id;*/

    //https://www.bezkoder.com/jpa-one-to-many/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)//we use Fetchtype.LAZY for faster performance
    @JoinColumn(name = "users_id", nullable = false)//We also use the @JoinColumn annotation to specify the foreign key column
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Users users;



}
