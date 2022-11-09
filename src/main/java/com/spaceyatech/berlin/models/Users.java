package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@Entity
@AllArgsConstructor
public class Users extends BaseEntity{

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID userId;*/
    @Column(length=50)
    private String username;
    @Column(length=50)
    private String email;
    @Column(length=60)
    private String password;
    @Column(length=45)
    private String phone_number;
    @Column(length=45)
    private String userscol;
    @Column(length=45)
    private String verification_code;
    @Column(length=6)
    private Timestamp date_created;


}
