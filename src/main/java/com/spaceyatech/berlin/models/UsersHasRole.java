package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;

@Builder
@Data
@Entity
@AllArgsConstructor
public class UsersHasRole{


    private String users_id;
    private String role_id;
}
